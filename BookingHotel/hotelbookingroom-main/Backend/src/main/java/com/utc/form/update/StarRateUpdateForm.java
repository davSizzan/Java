package com.utc.form.update;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.PositiveOrZero;

@Data
public class StarRateUpdateForm {

    @PositiveOrZero(message = "")
    private int ImageName;
}
