package com.utc.dto.signin_up;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SignUpDTO {

    @NotBlank
    @NonNull
    @Length(max = 100,message = "First Name's max length is 100 character")
    private String firstName;

    @NotBlank
    @NonNull
    @Length(max = 100,message = "Last Name's max length is 100 character")
    private String lastName;

    @NotBlank
    @NonNull
    @Length(max = 100,message = "Last Name's max length is 100 character")
    private String userName;

    @NotBlank
    @NonNull
    @Length(min = 12,max = 100,message = "Id card's max length is 100 character and min length 12 character")
    private String idCard;

    @NotBlank
    @NonNull
    @Length(max = 100,message = "Email's max length is 100 character")
    @Email(message = "Email has incorrect syntax or does not exist")
    private String email;

    @NonNull
    @NotBlank
    @NotNull
    @Length(min = 6,max = 20,message = "Password must has between 6 , 20 character")
    private String password;

    @NonNull
    @NotBlank
    @NotNull(message = "Country does not null")
    private String addressCountry;

    @NonNull
    @NotBlank
    @NotNull(message = "City does not null")
    private String addressCity;
}
