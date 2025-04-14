package com.rt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.dao.RegisterDao;
import com.rt.entity.RegisterEntity;

@Service
public class RegisterService {
    @Autowired
    private RegisterDao registerDao;

    public boolean signUp(RegisterEntity register) {
        try {
            // You can perform any business logic validations here before calling the DAO
            return registerDao.signUp(register);
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            e.printStackTrace();
            return false;
        }
    }
}