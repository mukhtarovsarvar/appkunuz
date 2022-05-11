package com.company.validation;


import com.company.dto.CategoryDto;
import com.company.exception.AppBadRequestException;

public class CategoryValidation {



   public static void isValid(CategoryDto dto) {
       ArticleTypeValidation.validation(dto.getNameEn(), dto.getNameUz(), dto.getNameRu(), dto.getKey());
   }
}
