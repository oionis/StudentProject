package com.company.validator;

import com.company.answer.AnswerStudent;
import com.company.domain.StudentOrder;

public class StudentValidator {

    public AnswerStudent checkStudent (StudentOrder so){
        System.out.println("Checking on student");
        return new AnswerStudent();
    }

}
