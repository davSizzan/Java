package com.utc.form.create;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
public class RoomRateDiscountCreateForm {

    @NotBlank(message = "Can't be left empty")
    @Positive(message = "Rate must be greater than 0")
    private double rate;

    @NotBlank(message = "Can't be left empty")
    @Future(message = "The date is not in the future")
    private Date startMonth;

    @NotBlank(message = "Can't be left empty")
    @Future(message = "The date is not in the future")
    private Date endMonth;

    @NotBlank(message = "Can't be left empty")
    @Pattern(regexp = "TRAVEL | GO ON BUSSINESS | RESORT",message = "Room type must be TRAVEL, GO ON BUSSINESS or RESORT")
    @Positive(message = "Rate must be greater than 0")
    private int roomTypeId;

}
