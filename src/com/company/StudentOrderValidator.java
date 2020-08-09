package com.company;

import com.company.answer.AnswerChildren;
import com.company.answer.AnswerCityRegister;
import com.company.answer.AnswerStudent;
import com.company.answer.AnswerWedding;
import com.company.domain.StudentOrder;
import com.company.mail.MailSender;
import com.company.validator.ChildrenValidator;
import com.company.validator.CityRegisterValidator;
import com.company.validator.StudentValidator;
import com.company.validator.WeddingValidator;

import java.util.LinkedList;
import java.util.List;

public class StudentOrderValidator {

    private CityRegisterValidator cityRegisterValidator;
    private WeddingValidator weddingValidator;
    private ChildrenValidator childrenValidator;
    private StudentValidator studentValidator;
    private MailSender mailSender;

    public StudentOrderValidator() {
        cityRegisterValidator = new CityRegisterValidator();
        weddingValidator = new WeddingValidator();
        childrenValidator = new ChildrenValidator();
        studentValidator = new StudentValidator();
        mailSender = new MailSender();
    }

    public static void main(String[] args) {
        StudentOrderValidator sov = new StudentOrderValidator();
        sov.checkAll();
    }

    public void checkAll() {

        List <StudentOrder> soList = readStudentOrders();
        for (StudentOrder i : soList) {
            checkOneOrder(i);
        }

    }

    public void checkOneOrder(StudentOrder so) {
        AnswerCityRegister cityRegister = checkCityRegister(so);
//        AnswerChildren childAns = checkChildren(so);
//        AnswerStudent answearStudent = checkStudent(so);
//        AnswerWedding answearWedding = checkWedding(so);
//       sendMail(so);
    }


    public List<StudentOrder> readStudentOrders() {
        int n = 5;
        List<StudentOrder> soList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            StudentOrder so = SaveStudentOrder.buildStudentOrder(i);
            soList.add(so);
        }
        return soList;
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        return cityRegisterValidator.checkCityRegister(so);
    }

    public AnswerWedding checkWedding(StudentOrder so) {
        return weddingValidator.checkWedding(so);
    }

    public AnswerStudent checkStudent(StudentOrder so) {
        return studentValidator.checkStudent(so);
    }

    public AnswerChildren checkChildren(StudentOrder so) {
        return childrenValidator.checkChildren(so);
    }

    public void sendMail(StudentOrder so) {
        mailSender.sendMail(so);
    }
}
