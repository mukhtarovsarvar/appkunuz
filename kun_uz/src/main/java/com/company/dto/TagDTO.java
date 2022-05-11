package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDTO extends BasicDto {

    @NotBlank(message = "key not valid")
    private String key;

    private String name;

    @NotBlank(message = "name uz not valid")
    private String nameUz;
    @NotBlank(message = "name  ru not valid")
    private String nameRu;
    @NotBlank(message = "name en not valid")
    private String nameEn;

    private Boolean visible;

    private Integer profileId;

}
