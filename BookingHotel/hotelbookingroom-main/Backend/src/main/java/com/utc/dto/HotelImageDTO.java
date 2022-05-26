package com.utc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class HotelImageDTO {

    @JsonFormat(pattern = "Image URL")
    private String imageName;

    @JsonFormat(pattern = "Name Hotel")
    private String hotelName;
}
