package com.utc.form.create;

import lombok.Getter;

import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class HotelCreateForm {

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "Length exceeds 100 characters")
    private String name;

    @NotBlank(message = "Can't be left empty")
    @Email(message = "Email has incorrect syntax or does not exist")
    private String email;

    @NotBlank(message = "Can't be left empty")
    @URL(message = "URL is wrong syntax")
    private String website;

    @NotBlank(message = "Can't be left empty")
    private String description;

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "Length exceeds 100 characters")
    private String country;

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "Length exceeds 100 characters")
    private String city;

    @NotNull(message = "Can't be left empty")
    @PositiveOrZero(message = "The room count must be greater than or equal 0")
    private int roomCount;
}
