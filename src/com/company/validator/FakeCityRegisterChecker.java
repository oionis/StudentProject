package com.company.validator;

import com.company.answer.CityRegisterResponse;
import com.company.domain.Adult;
import com.company.domain.Child;
import com.company.domain.Person;
import com.company.exception.CityRegisterException;
import com.company.exception.TransportException;

import java.util.Arrays;

public class FakeCityRegisterChecker implements CityRegisterChecker {

    private static final String[] SERIAL = {"KH", "PL", "KV"};
    public static final String GOOD = "1000";
    public static final String BAD = "1001";
    private static final String ERROR = "1002";
    public static final String ERROR_T = "1003";


    public CityRegisterResponse checkPerson(Person person)
            throws CityRegisterException, TransportException {

        CityRegisterResponse ans = new CityRegisterResponse();
//        if (person instanceof Adult) {
//            Adult a = (Adult) person;
//            String ps = a.getPassportSerial();
//            if (Arrays.asList(SERIAL).contains(ps)) {
//                ans.setExisting(true);
//                ans.setTemporal(false);
//            } else if (ps.equals(ERROR)) {
//                CityRegisterException ex = new CityRegisterException("404", "GRN ERROR");
//                throw ex;
//            } else if (ps.equals(ERROR_T)){
//                TransportException ex = new TransportException("Transport error");
//                throw ex;
//            } else {
//                ans.setExisting(false);
//            }
//        }
        if (person instanceof Adult){
            Adult t = (Adult) person;
            String ps = t.getPassportSerial();
            if (ps.equals(GOOD)) {
                ans.setExisting(true);
                ans.setTemporal(false);
            } else if (ps.equals(BAD)){
                ans.setExisting(false);
            } else if (ps.equals(ERROR)){
                CityRegisterException ex = new CityRegisterException("404", "GRN Error");
                throw ex;
            } else if (ps.equals(ERROR_T)){
                TransportException ex = new TransportException("Transport Error");
                throw ex;
            }

        }

        if (person instanceof Child){
            ans.setExisting(true);
            ans.setTemporal(true);
        }

        System.out.println(ans);
        return ans;
    }

//    private boolean exist (String str){
//        for (String a: SERIAL) {
//            if(a.equals(str)){
//                return true;
//            }
//        }
//        return false;
//    }

}
