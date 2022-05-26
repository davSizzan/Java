package com.utc.form.filter;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BookingFilter {

    private String guestsIdCard;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkInDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkOutDate;

}
