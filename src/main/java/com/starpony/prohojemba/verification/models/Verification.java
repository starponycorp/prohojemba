package com.starpony.prohojemba.verification.models;


import java.util.Objects;

public class Verification {
    private final String email;
    private final VerificationType type;
    private final String code;

    public Verification(String email, VerificationType type, String code) {
        this.email = email;
        this.type = type;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public VerificationType getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof Verification verification)
            return Objects.equals(email, verification.email) &&
                    Objects.equals(type, verification.type) &&
                    Objects.equals(code, verification.code);

        return false;
    }
}
