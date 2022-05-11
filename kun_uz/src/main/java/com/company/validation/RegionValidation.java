package com.company.validation;

import com.company.dto.RegionDto;

public class RegionValidation {


    public static void isValid(RegionDto dto) {
        ArticleTypeValidation.validation(dto.getNameEn(), dto.getNameUz(), dto.getNameRu(), dto.getKey());
    }
}
