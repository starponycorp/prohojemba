package com.starpony.prohojemba.services;

import com.starpony.prohojemba.dto.AccountEditDto;
import com.starpony.prohojemba.exceptions.ItemFormException;
import com.starpony.prohojemba.exceptions.ItemNotFoundException;
import com.starpony.prohojemba.models.Account;
import com.starpony.prohojemba.repositories.AccountsDatabaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class UsersService {
    private final AccountsDatabaseRepository accountsRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(AccountsDatabaseRepository accountsRepository, PasswordEncoder passwordEncoder) {
        this.accountsRepository = accountsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account getOne(int id) {
        return accountsRepository.getById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Account with id=%s not found", id)));
    }

    public Account update(int id, AccountEditDto accountEditDto) {
        Account account = accountsRepository.getById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Account with id=%s not found", id)));

        if (accountEditDto.getEmail() != null &&
                !accountEditDto.getEmail().equals(account.getEmail()))
            if (!passwordEncoder.matches(
                    accountEditDto.getCurrentPassword(), account.getEncodedPassword()))
                throw new ItemFormException("Incorrect current password");

        if (accountEditDto.getNewPassword() != null)
            if (!passwordEncoder.matches(
                    accountEditDto.getCurrentPassword(), account.getEncodedPassword()))
                throw new ItemFormException("Incorrect current password");

        account = new Account(
                account.getId(),
                accountEditDto.getEmail() == null ? account.getEmail() : accountEditDto.getEmail(),
                accountEditDto.getNewPassword() == null ? account.getEncodedPassword() :
                        passwordEncoder.encode(accountEditDto.getNewPassword()),
                false,
                accountEditDto.getUsername() == null ? account.getUsername() : accountEditDto.getUsername(),
                accountEditDto.getAvatar() == null ? account.getAvatar() : accountEditDto.getAvatar(),
                account.getPermissions()
        );

        accountsRepository.update(account);
        return account;
    }
}
