package com.utc.dto;

import lombok.Data;

@Data
public class RoomDTO {

    private String number;

    private String status;

    private String roomTypeStatus;

    private String hotelName;

    private Double roomTypeCost;
}
