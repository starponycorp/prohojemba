package com.starpony.prohojemba.services;

import com.starpony.prohojemba.exceptions.ItemNotFoundException;
import com.starpony.prohojemba.models.User;
import com.starpony.prohojemba.repositories.UsersDatabaseRepository;
import com.starpony.prohojemba.dto.ProfileEditDto;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class UsersService {
    private final UsersDatabaseRepository userRepository;

    @Autowired
    public UsersService(UsersDatabaseRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOne(int id) {
        return userRepository.getById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("User with id=%s not found", id)));
    }

    public User update(int id, @Valid ProfileEditDto profileEditDto) {
        User user = new User(id, null, null, false, profileEditDto.getUsername(), profileEditDto.getAvatar(), null);
        return getOne(id);
    }
}
