package com.company.validator;

import com.company.answer.AnswerChildren;
import com.company.domain.StudentOrder;

public class ChildrenValidator {

    public AnswerChildren checkChildren (StudentOrder so){
        System.out.println("Checking on children");
        return new AnswerChildren();
    }

}
