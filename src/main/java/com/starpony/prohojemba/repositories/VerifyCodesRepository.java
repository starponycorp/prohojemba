package com.starpony.prohojemba.repositories;

import com.starpony.prohojemba.enums.VerifyType;


public interface VerifyCodesRepository {
    void create(VerifyType verifyType, String email, String code);

    void delete(VerifyType verifyType, String email);
}
