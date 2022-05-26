package com.utc.form.update;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class HotelUpdateForm {

    @URL(message = "URL is wrong syntax")
    private String website;

    @PositiveOrZero(message = "The room count must be greater than or equal 0")
    private int roomCount;

    private String description;
}
