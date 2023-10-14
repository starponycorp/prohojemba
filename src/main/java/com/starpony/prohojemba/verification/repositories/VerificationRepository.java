package com.starpony.prohojemba.verification.repositories;

import com.starpony.prohojemba.verification.models.Verification;
import com.starpony.prohojemba.verification.models.VerificationType;

import java.util.Optional;


public interface VerificationRepository {
    Optional<Verification> get(VerificationType verificationType, String email);
    void create(Verification verification);
    void delete(VerificationType verificationType, String email);
}
