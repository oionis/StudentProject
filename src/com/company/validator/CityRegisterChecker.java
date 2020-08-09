package com.company.validator;

import com.company.answer.CityRegisterResponse;
import com.company.domain.Person;
import com.company.exception.CityRegisterException;
import com.company.exception.TransportException;

public interface CityRegisterChecker {
    CityRegisterResponse checkPerson (Person person)
            throws CityRegisterException, TransportException;

}
