package com.utc.form.update;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Email;

@Getter
@Setter
public class GuestsUpdateForm {

    @Email(message = "Email has incorrect syntax or does not exist")
    private String email;

    @CreditCardNumber(message = "Card code does not exist")
    private String creditCard;
}
