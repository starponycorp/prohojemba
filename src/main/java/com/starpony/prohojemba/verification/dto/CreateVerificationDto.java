package com.starpony.prohojemba.verification.dto;

import com.starpony.prohojemba.verification.models.VerificationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class CreateVerificationDto {
    @NotBlank
    @Email
    private final String email;
    @NotNull
    private final VerificationType verificationType;

    public CreateVerificationDto(String email, VerificationType verificationType) {
        this.email = email;
        this.verificationType = verificationType;
    }

    public String getEmail() {
        return email;
    }

    public VerificationType getVerificationType() {
        return verificationType;
    }
}
