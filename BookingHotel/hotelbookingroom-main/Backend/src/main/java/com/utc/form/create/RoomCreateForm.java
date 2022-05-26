package com.utc.form.create;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
public class RoomCreateForm {

    @NotBlank(message = "Can't be left empty")
    @Positive(message = "Cost must be greater than 0")
    private String number;

    @Pattern(regexp = "TRAVEL | GO ON BUSSINESS | RESORT",message = "Room type must be TRAVEL, GO ON BUSSINESS or RESORT")
    private int roomType;

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "Hotel name's length is max 100 characters")
    private String hotelName;
}
