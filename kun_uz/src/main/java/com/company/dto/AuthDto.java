package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter

public class AuthDto {
    @NotNull(message = "email null ku!")
    @Email(message = "email hato")                             // email ni tekshiradi
    private String email;
    // @NotNull(message = "password null ku!")                 // null ga tekshiradi
    @Size(min = 3,max = 15,message = "password wrong size!")   //sizeni oziga tekshiradi
    @NotBlank(message = "Password required")                   // kegan valueni trim() qiladi va size ga tekshiradi
    private String password;

}
