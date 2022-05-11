package com.company.validation;

import com.company.dto.ProfileDto;
import com.company.exception.AppBadRequestException;

public class ProfileValidation {

    public static void isValid(ProfileDto dto){
        AuthValidation.isValid(dto.getEmail(), dto.getName(), dto.getPassword(), dto.getSurname());

    }

}
