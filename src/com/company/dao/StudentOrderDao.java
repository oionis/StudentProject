package com.company.dao;

import com.company.domain.StudentOrder;
import com.company.exception.DaoException;

public interface StudentOrderDao {
    Long saveStudentOrder (StudentOrder so) throws DaoException;
}
