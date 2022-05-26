package com.utc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


@Data
public class AddressDTO {

    @JsonFormat(pattern = "AddressID")
    private int id;

    @JsonFormat(pattern = "AddressCountry")
    private String country;

    @JsonFormat(pattern = "AddressCity")
    private String city;

}
