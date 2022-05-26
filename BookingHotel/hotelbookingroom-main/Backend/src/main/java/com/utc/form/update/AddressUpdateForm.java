package com.utc.form.update;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AddressUpdateForm {

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "Length exceeds 100 characters")
    private String country;

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "Length exceeds 100 characters")
    private String city;
}
