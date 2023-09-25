package com.starpony.prohojemba.repositories;

import com.starpony.prohojemba.enums.VerifyType;

import java.util.Optional;


public interface VerifyCodesRepository {
    void create(VerifyType verifyType, String email, String code);

    Optional<String> get(VerifyType verifyType, String email);

    void delete(VerifyType verifyType, String email);
}
