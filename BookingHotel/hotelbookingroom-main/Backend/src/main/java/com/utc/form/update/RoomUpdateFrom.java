package com.utc.form.update;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class RoomUpdateFrom {

    @Pattern(regexp = "DRUM | BOOKING",message = "Room type must be DRUM or BOOKING")
    private String status;
}
