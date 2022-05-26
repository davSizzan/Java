package com.utc.form.create;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class HotelServiceCreateForm {

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "Length exceeds 100 characters")
    private String name;

    @NotBlank(message = "Can't be left empty")
    @Positive(message = "Cost must be greater than 0")
    private Double cost;

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "Length exceeds 100 characters")
    private String hotelName;
}
