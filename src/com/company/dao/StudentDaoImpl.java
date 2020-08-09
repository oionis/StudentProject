package com.company.dao;

import com.company.config.Config;
import com.company.domain.*;
import com.company.exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;

public class StudentDaoImpl implements StudentOrderDao {

    private static final String INSERT_ORDER =
            "INSERT INTO sp_student_order(" +
                    "            student_order_date, student_order_status, h_surname, h_given_name," +
                    "            h_patronymic, h_date_of_birth, h_post_index, h_street_code, " +
                    "            h_building, h_apartment, h_passport_seria, h_passport_number, h_passport_date," +
                    "            h_passport_office_id, h_university_id, h_university_number, w_surname, w_given_name, " +
                    "            w_patronymic, w_date_of_birth, w_post_index, w_street_code, w_building, " +
                    "            w_apartment, w_passport_seria, w_passport_number, w_passport_date, w_passport_office_id," +
                    "            w_university_id, w_university_number, certificate_id, register_office_id, marriage_date)" +
                    "    VALUES (?, ?, ?, ?," +
                    "            ?, ?, ?, ?, " +
                    "            ?, ?, ?, ?, ?," +
                    "            ?, ?, ?, ?, ?, " +
                    "            ?, ?, ?, ?, ?, " +
                    "            ?, ?, ?, ?, ?," +
                    "            ?, ?, ?, ?, ?);";

    private static final String INSERT_CHILD =
            "INSERT INTO sp_student_child(" +
                    "            student_order_id, c_surname, c_given_name, " +
                    "            c_patronymic, c_date_of_birth, c_post_index, c_street_code, c_building, " +
                    "            c_apartment, c_certificate_number, c_certificate_date, c_register_office_id)" +
                    "    VALUES (?, ?, ?, " +
                    "            ?, ?, ?, ?, ?, " +
                    "            ?, ?, ?, ? );";

    // TODO refactoring = make one method
    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return con;
    }

    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {
        Long resault = -1l;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String[] {"student_order_id"})) {

            con.setAutoCommit(false);
            try {
                int id = 1;
                stmt.setTimestamp(id++, java.sql.Timestamp.valueOf(LocalDateTime.now()));
                stmt.setInt(id++, StudentOrderStatus.START.ordinal());

                id = setParamsForAdult(so.getHusband(), id, stmt);
                id = setParamsForAdult(so.getWife(), id, stmt);

                stmt.setString(id++, so.getMarriageCertificateID());
                stmt.setLong(id++, so.getMarriageOffice().getOfficeId());
                stmt.setDate(id, java.sql.Date.valueOf(so.getMarriageDate()));

                stmt.executeUpdate();
                ResultSet gkRs = stmt.getGeneratedKeys();
                if (gkRs.next()){
                    resault = gkRs.getLong(1);
                }

                saveChildren (con, so, resault);
                con.commit();
            } catch (SQLException ex){
                con.rollback();
                throw ex;
            }

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return resault;
    }

    private void saveChildren(Connection con, StudentOrder so, Long soId) throws SQLException{
        try (PreparedStatement stmt = con.prepareStatement(INSERT_CHILD) ){
            for (Child child: so.getChildren() ){
                int id = 1;
                stmt.setLong(id++, soId);
                setParamsForChild(child, id, stmt);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }


    }

    private int setParamsForPerson(Person person, int id, PreparedStatement stmt) throws SQLException {
        stmt.setString(id++, person.getSurName());
        stmt.setString(id++, person.getGivenName());
        stmt.setString(id++, person.getPatronymic());
        stmt.setDate(id++, java.sql.Date.valueOf(person.getDateOfBirth()));
        stmt.setString(id++, person.getAddress().getPostCode());
        stmt.setLong(id++, person.getAddress().getStreet().getStreetCode());
        stmt.setString(id++, person.getAddress().getBuilding());
        stmt.setString(id++, person.getAddress().getApartment());

        return id;
    }

    private int setParamsForAdult(Adult adult, int id, PreparedStatement stmt) throws SQLException {
        id = setParamsForPerson(adult, id, stmt);
        stmt.setString(id++, adult.getPassportSerial());
        stmt.setString(id++, adult.getPassportNumber());
        stmt.setDate(id++, java.sql.Date.valueOf(adult.getIssueDate()));
        stmt.setLong(id++, adult.getIssueDepartment().getOfficeId());
        stmt.setLong(id++, adult.getUniversity().getUniversityId());
        stmt.setString(id++, adult.getStudentID());
        return id;
    }

    private void setParamsForChild(Child child, int id, PreparedStatement stmt) throws SQLException {
        id = setParamsForPerson(child, id, stmt);
        stmt.setString(id++, child.getCertificateNumber());
        stmt.setDate(id++, java.sql.Date.valueOf(child.getIssueDate()));
        stmt.setLong(id, child.getIssueDepartment().getOfficeId());
    }



}
