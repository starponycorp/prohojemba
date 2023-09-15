package com.starpony.prohojemba.services;

import com.starpony.prohojemba.exceptions.ItemNotFoundException;
import com.starpony.prohojemba.models.Account;
import com.starpony.prohojemba.repositories.AccountsDatabaseRepository;
import com.starpony.prohojemba.dto.ProfileEditDto;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class UsersService {
    private final AccountsDatabaseRepository userRepository;

    @Autowired
    public UsersService(AccountsDatabaseRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Account getOne(int id) {
        return userRepository.getById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Account with id=%s not found", id)));
    }

    public Account update(int id, @Valid ProfileEditDto profileEditDto) {
        Account account = new Account(id, null, null, false, profileEditDto.getUsername(), profileEditDto.getAvatar(), null);
        return getOne(id);
    }
}
