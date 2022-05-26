package com.utc.form.update;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class RoomTypeUpdateForm {

    @Positive(message = "Rate must be greater than 0")
    private double cost;
}
