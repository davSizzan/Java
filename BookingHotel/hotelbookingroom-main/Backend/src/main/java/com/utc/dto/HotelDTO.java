package com.utc.dto;

import lombok.Data;

@Data
public class HotelDTO {

    private String name;

    private String website;

    private String email;

    private String description;

    private int roomCount;

    private String addressCity;
}
