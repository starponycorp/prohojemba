package com.starpony.prohojemba.dto;


import jakarta.validation.constraints.NotBlank;

public class TokensUpdateDto {
    @NotBlank(message = "Refresh token cannot be empty")
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
