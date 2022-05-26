package com.utc.dto.responseJWT;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

@Data
public class ResponseJWTDTO {

    @NonNull
    @JsonFormat(pattern = "JWTToken")
    private String token;

    @NonNull
    @JsonFormat(pattern = "User Name")
    private String userName;

    @NonNull
    @JsonFormat(pattern = "Type")
    private String guestsType;
}
