package com.utc.form.create;

import com.utc.entity.UserServices;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
public class BookingCreateForm {

    @NotBlank(message = "Can't be left empty")
    @Future(message = "The date is not in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkIn;

    @NotBlank(message = "Can't be left empty")
    @Future(message = "The date is not in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkOut;

    @NotBlank(message = "Can't be left empty")
    private String hotelName;

}
