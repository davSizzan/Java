package com.utc.form.create;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class HotelImageCreateForm {

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "Length exceeds 100 characters")
    private String imageName;

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "Length exceeds 100 characters")
    private String hotelName;

}
