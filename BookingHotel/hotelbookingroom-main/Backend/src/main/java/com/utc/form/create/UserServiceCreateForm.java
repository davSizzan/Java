package com.utc.form.create;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class UserServiceCreateForm {

    @NotBlank(message = "Can't be left empty")
    @Length(max = 100,message = "HotelServices Name's max length is 100 character")
    private String hotelServicesName;

    @NotBlank(message = "Can't be left empty")
    @Positive(message = "Booking id must be greater than 0")
    private int bookingId;
}
