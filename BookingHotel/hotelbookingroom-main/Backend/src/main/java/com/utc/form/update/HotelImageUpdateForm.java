package com.utc.form.update;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class HotelImageUpdateForm {

    @Length(max = 100,message = "Length exceeds 100 characters")
    private String imageName;
}
