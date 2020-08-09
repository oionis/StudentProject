package com.company;

import com.company.dao.DictionaryDao;
import com.company.dao.DictionaryDaoImpl;
import com.company.dao.StudentDaoImpl;
import com.company.dao.StudentOrderDao;
import com.company.domain.*;

import java.time.LocalDate;
import java.util.List;

public class SaveStudentOrder {

    public static void main(String[] args) throws Exception{
//        List<Street> streets = new DictionaryDaoImpl().findStreets("");
//        for (Street street : streets){
//            System.out.println(street.getStreetName());
//        }
//        System.out.println("\n");
//        List<PassportOffice> po = new DictionaryDaoImpl().findPassportOffices("010020000000");
//        for (PassportOffice passportOffice : po){
//            System.out.println(passportOffice.getOfficeName());
//        }
//        System.out.println("\n");
//        List<RegisterOffice> ro = new DictionaryDaoImpl().findRegisterOffice("010010000000");
//        for (RegisterOffice registerOffice : ro){
//            System.out.println(registerOffice.getOfficeName());
//        }
//        System.out.println("\n");
//        List<CountryArea> ca1 = new DictionaryDaoImpl().findAreas("");
//        for (CountryArea ca : ca1){
//            System.out.println(ca.getAreaId() + ":" + ca.getAreaName());
//        }
//        System.out.println("-----");
//        List<CountryArea> ca2 = new DictionaryDaoImpl().findAreas("020000000000");
//        for (CountryArea ca : ca2){
//            System.out.println(ca.getAreaId() + ":" + ca.getAreaName());
//        }
//        System.out.println("-----");
//        List<CountryArea> ca3 = new DictionaryDaoImpl().findAreas("020010000000");
//        for (CountryArea ca : ca3){
//            System.out.println(ca.getAreaId() + ":" + ca.getAreaName());
//        }
//        System.out.println("-----");
//        List<CountryArea> ca4 = new DictionaryDaoImpl().findAreas("020010010000");
//        for (CountryArea ca : ca4){
//            System.out.println(ca.getAreaId() + ":" + ca.getAreaName());
//        }

        StudentOrder s = buildStudentOrder(10);
        StudentOrderDao dao = new StudentDaoImpl();
        Long id = dao.saveStudentOrder(s);
        System.out.println(id);

       //buildStudentOrder(10);
    }

    static void saveStudentOrder (StudentOrder so){
        System.out.println("Save student order");
    }

    static StudentOrder buildStudentOrder (long id){
        StudentOrder so = new StudentOrder();
        so.setStudentOrderID(id);
        so.setMarriageCertificateID("" + id + (int) (Math.random()*1000));
            so.setMarriageDate(LocalDate.of(2015, 12, 4));
            RegisterOffice ro = new RegisterOffice(1l, "", "");
        so.setMarriageOffice(ro);

        Street street = new Street(1l, "Tmp street");

        Address address = new Address(street, "54", "5", "76200");

        //Husband
        Adult husband = new Adult("Alex", "Sidorov", "Konstantinov", LocalDate.of(1970, 10, 25));
        husband.setPassportSerial("100" + id);

        husband.setPassportNumber("" + id + id*2 + id*3);
        husband.setIssueDate(LocalDate.of(1986, 12, 20));
        PassportOffice passportOffice = new PassportOffice(1l, "", "");
        husband.setIssueDepartment(passportOffice);
        husband.setStudentID("" + id + ((int) Math.random() * 1000));
        husband.setAddress(address);
        husband.setUniversity(new University(2l, ""));
        husband.setStudentID("HH12345");

        //Wife
        Adult wife = new Adult("Galina", "Sidorova", "Petrova", LocalDate.of(1975, 4, 20));
        wife.setPassportSerial("100" + id);
        wife.setPassportNumber("" + id + id*2 + id*3);
        wife.setIssueDate(LocalDate.of(1991, 11, 20));
        PassportOffice passportOffice2 = new PassportOffice(2l, "", "");
        wife.setIssueDepartment(passportOffice2);
        wife.setStudentID("" + id + ((int) Math.random() * 1000));
        wife.setAddress(address);
        wife.setUniversity(new University(1l, ""));
        wife.setStudentID("WW12345");

        //Child
        Child child1 = new Child("Georgiy", "Sidorov", "Alexeyevich", LocalDate.of(1970, 10, 25));
        child1.setCertificateNumber("" + id + ((int) Math.random()*100));
        child1.setIssueDate(LocalDate.of(2000, 01, 15));
        RegisterOffice ro2 = new RegisterOffice(2l, "", "");

        child1.setIssueDepartment(ro2);
        child1.setAddress(address);
        //Child
        Child child2 = new Child("Valeria", "Sidorova", "Alexeyevna", LocalDate.of(1970, 10, 25));
        child2.setCertificateNumber("" + id + ((int) Math.random()*100));
        child2.setIssueDate(LocalDate.of(2000, 01, 15));
        RegisterOffice ro3 = new RegisterOffice(3l, "", "");
        child2.setIssueDepartment(ro3);
        child2.setAddress(address);

        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);
        //System.out.println(so.getChildren().size());


        //printStudentOrder(so);
        so.setStudentOrderID(3);

        return so;
    }

    static void printStudentOrder (StudentOrder so){
        System.out.println(so.getStudentOrderID());
    }
}
