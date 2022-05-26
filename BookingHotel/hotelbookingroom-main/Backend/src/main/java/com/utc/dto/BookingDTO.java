package com.utc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BookingDTO {

    private String guestsIdCard;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateBooking;

    private int timeLive;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkIn;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkOut;

    private String hotelName;

    private double amount;
}
