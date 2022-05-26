package com.utc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RoomBookDTO {

    private String bookingGuestsFullName;

    private String bookingGuestsIdCard;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookingDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookingCheckIn;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookingCheckOut;

    private String roomNumber;

    private String roomRoomTypeName;

    private double bookingAmount;

    private String bookingStatus;

}
