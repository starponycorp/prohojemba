package com.starpony.prohojemba.users;

import com.starpony.prohojemba.users.dto.EditProfileDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class UserService {
    public User getOne(int id) {
        return null;
    }

    public User updateProfile(int id, @Valid EditProfileDto editProfileDto) {
        return null;
    }
}
