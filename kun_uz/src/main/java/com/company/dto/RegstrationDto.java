package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegstrationDto {

    @NotBlank(message = "name not valid")
    private String name;
    @NotBlank(message = "surname  not valid")
    private String surname;
    @NotBlank(message = "email not valid")
    private String email;
    @NotBlank(message = "password not valid")
    private String password;
}
