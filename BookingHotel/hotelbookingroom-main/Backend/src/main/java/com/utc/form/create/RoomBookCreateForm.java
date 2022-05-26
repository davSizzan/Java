package com.utc.form.create;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class RoomBookCreateForm {

    @NotBlank(message = "Can't be left empty")
    private String roomNumber;

    @NotBlank(message = "Can't be left empty")
    private String hotelName;

    @NotBlank(message = "Can't be left empty")
    private String bookingGuestsIdCard;

}
