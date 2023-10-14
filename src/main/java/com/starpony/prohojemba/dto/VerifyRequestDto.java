package com.starpony.prohojemba.dto;

public class VerifyRequestDto {
    private String email;
    private VerifyType verifyType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VerifyType getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(VerifyType verifyType) {
        this.verifyType = verifyType;
    }
}
