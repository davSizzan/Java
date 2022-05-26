package com.utc.form.create;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GuestsCreateForm {

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "First Name more than 100 characters")
    private String firstName;

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "First Name more than 100 characters")
    private String lastName;

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "First Name more than 100 characters")
    private String userName;

    @NotBlank(message = "Can't be left empty")
//    @CreditCardNumber(message = "Card code does not exist")
    private String idCard;

    @NotBlank(message = "Can't be left empty")
    @Email(message = "Email has incorrect syntax or does not exist")
    private String email;

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "Length exceeds 100 characters")
    private String country;

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "Length exceeds 100 characters")
    private String city;

}
