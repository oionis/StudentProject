package com.company.validator;

import com.company.answer.AnswerWedding;
import com.company.domain.StudentOrder;

public class WeddingValidator {
    public AnswerWedding checkWedding (StudentOrder so){
        System.out.println("Checking on wedding");
        return new AnswerWedding();
    }
}
