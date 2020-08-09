package com.company.dao;

import com.company.domain.CountryArea;
import com.company.domain.PassportOffice;
import com.company.domain.RegisterOffice;
import com.company.domain.Street;
import com.company.exception.DaoException;

import java.util.List;

public interface DictionaryDao {
    List<Street> findStreets(String pattern) throws DaoException;
    List<PassportOffice> findPassportOffices (String areaId) throws DaoException;
    List<RegisterOffice> findRegisterOffice (String areaId) throws DaoException;
    List<CountryArea> findAreas (String areaId) throws DaoException;
}
