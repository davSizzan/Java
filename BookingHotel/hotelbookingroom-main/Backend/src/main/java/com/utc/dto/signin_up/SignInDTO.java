package com.utc.dto.signin_up;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SignInDTO {

    @NonNull
    @NotBlank
    @NotNull(message = "User name not null")
    private String userName;

    @NonNull
    @NotBlank
    @NotNull(message = "Password not null")
    private String password;
}
