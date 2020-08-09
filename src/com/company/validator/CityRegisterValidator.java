package com.company.validator;

import com.company.answer.AnswerCityRegister;
import com.company.answer.AnswerCityRegisterItem;
import com.company.answer.CityRegisterResponse;
import com.company.domain.Child;
import com.company.domain.Person;
import com.company.domain.StudentOrder;
import com.company.exception.CityRegisterException;
import com.company.exception.TransportException;

public class CityRegisterValidator {

    public String hostName;
    private CityRegisterChecker personChecker;
    public static final String NO_GRN = "NO_GRN";

    public CityRegisterValidator() {
        personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        AnswerCityRegister answearCityRegister = new AnswerCityRegister();

        answearCityRegister.addItem(checkPerson(so.getHusband()));
        answearCityRegister.addItem(checkPerson(so.getWife()));
        for (Child child : so.getChildren()){
            answearCityRegister.addItem(checkPerson(child));
        }

        return answearCityRegister;
    }

    private AnswerCityRegisterItem checkPerson (Person person){
        AnswerCityRegisterItem.CityStatus status;
        AnswerCityRegisterItem.CityError error = null;

        try {
           CityRegisterResponse tmp = personChecker.checkPerson(person);
           status = tmp.isExisting() ?
                   AnswerCityRegisterItem.CityStatus.YES :
                   AnswerCityRegisterItem.CityStatus.NO;
        } catch (CityRegisterException ex)  {
            ex.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(ex.getCode(), ex.getMessage());

        } catch (TransportException ex) {
            ex.printStackTrace();
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(NO_GRN, ex.getMessage());
        }

        AnswerCityRegisterItem ans = new AnswerCityRegisterItem(status, person, error);

        return ans;

    }

}
