package com.utc.form.create;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public class StarRateCreateForm {

    @NotBlank(message = "Can't be left empty")
    @PositiveOrZero(message = "")
    public int nameImage;

    @NotBlank(message = "Can't be left empty")
    @Positive(message = "Rate must be greater than 0")
    private int hotelId;
}
