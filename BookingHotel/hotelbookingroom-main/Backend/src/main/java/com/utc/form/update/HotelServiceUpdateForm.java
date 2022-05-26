package com.utc.form.update;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class HotelServiceUpdateForm {

    @Positive(message = "Cost must be greater than 0")
    private double cost;
}
