package com.utc.form.update;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
public class RoomRateDiscountUpdateForm {

    @Positive(message = "Rate must be greater than 0")
    private double rate;

    @Future(message = "The date is not in the future")
    private Date startMonth;

    @Future(message = "The date is not in the future")
    private Date endMonth;
}
