package com.starpony.prohojemba.dto;


public class TokensPairDto {
    private final String accessToken;
    private final String refreshToken;
    private final String type = "Bearer";

    public TokensPairDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getType() {
        return type;
    }
}
