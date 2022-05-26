package com.utc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RoomBookClientDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookingCheckIn;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookingCheckOut;

    private String roomNumber;

    private String roomHotelName;
}
