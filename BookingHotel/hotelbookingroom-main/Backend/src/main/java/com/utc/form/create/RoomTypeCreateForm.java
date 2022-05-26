package com.utc.form.create;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
public class RoomTypeCreateForm {

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "Name's max length is 100 character")
    private String name;

    @NotBlank(message = "Can't be left empty")
    @Positive(message = "Rate must be greater than 0")
    private double cost;

    @NotBlank(message = "Can't be left empty")
    @Pattern(regexp = "TRAVEL | GO ON BUSSINESS | RESORT",message = "Room type must be TRAVEL, GO ON BUSSINESS or RESORT")
    private String typeRoom;
}
