package com.utc.form.update;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class BookingUpdateForm {

    @Pattern(regexp = "BAKING|DIRECT" ,message = "The Payment Type must be BAKING or DIRECT")
    private String paymentType;

    @Pattern(regexp = "UNPAID|PAID" ,message = "The Status must be UNPAID or PAID")
    private String status;
}
