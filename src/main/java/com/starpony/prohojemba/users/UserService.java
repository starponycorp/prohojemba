package com.starpony.prohojemba.users;

import com.starpony.prohojemba.users.dto.EditProfileDto;
import com.starpony.prohojemba.users.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOne(int id) {
        return userRepository.findOne(id).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id=%s not found", id)));
    }

    public User updateProfile(int id, @Valid EditProfileDto editProfileDto) {
        return null;
    }
}
