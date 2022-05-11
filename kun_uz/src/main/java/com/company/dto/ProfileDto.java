package com.company.dto;

import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDto extends BasicDto {
    @NotBlank(message = "name not valid")
    private String name;
    @Email(message = "email hato")                             // email ni tekshiradi
    @NotBlank(message = "email not valid")
    private String email;
    @NotBlank(message = "surname not valid")
    private String surname;
    @NotBlank(message = "password not valid")
    private String password;
    private ProfileRole role;
    private ProfileStatus status;
    private String jwt;

}
