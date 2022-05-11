package com.company.validation;

import com.company.dto.ArticleTypeDto;
import com.company.exception.AppBadRequestException;

public class ArticleTypeValidation {

    public static void isValid(ArticleTypeDto dto){
        validation(dto.getNameEn(), dto.getNameUz(), dto.getNameRu(), dto.getKey());

    }

    static void validation(String nameEn, String nameUz, String nameRu, String key) {
        if(nameEn.trim().length()  <2){
            throw new AppBadRequestException("nameEn not valid");
        }
        if(nameUz.trim().length()  <2){
            throw new AppBadRequestException("nameUz not valid");
        }
        if(nameRu.trim().length()  <2){
            throw new AppBadRequestException("nameRu not valid");
        }
        if(key.trim().length()  <2){
            throw new AppBadRequestException("key not valid");
        }
    }
}
