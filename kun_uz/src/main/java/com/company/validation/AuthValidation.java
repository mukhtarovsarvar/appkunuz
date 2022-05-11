package com.company.validation;

import com.company.dto.ProfileDto;
import com.company.dto.RegstrationDto;
import com.company.exception.AppBadRequestException;

public class AuthValidation {


    public static void isValid(RegstrationDto dto){
        isValid(dto.getEmail(), dto.getName(), dto.getPassword(), dto.getSurname());

    }

    static void isValid(String email, String name, String password, String surname) {
        if(email.trim().length() < 4 || !email.contains("@")){
            throw new AppBadRequestException("email not valid");
        }

        if(name.trim().length() < 2){
            throw new AppBadRequestException("name not valid");
        }

        if(password.length() < 8){
            throw new AppBadRequestException("password not valid");
        }
        if (surname == null || surname.trim().length() < 3){
            throw new AppBadRequestException("surname not valid");
        }
    }
}
