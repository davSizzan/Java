package com.utc.form.filter;

import lombok.Data;

import java.util.Date;

@Data
public class RoomRateDiscountFilter {

    private Date startMonth;

    private double rate;
}
