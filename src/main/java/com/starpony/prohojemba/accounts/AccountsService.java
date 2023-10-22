package com.starpony.prohojemba.accounts;

import com.starpony.prohojemba.dto.AccountEditDto;
import com.starpony.prohojemba.accounts.models.Account;
import com.starpony.prohojemba.accounts.repositories.AccountsDatabaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class AccountsService {
    private final AccountsDatabaseRepository accountsRepository;
    private final VerifyCodesRepository verifyCodesRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountsService(AccountsDatabaseRepository accountsRepository, VerifyCodesRepository verifyCodesRepository, PasswordEncoder passwordEncoder) {
        this.accountsRepository = accountsRepository;
        this.verifyCodesRepository = verifyCodesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account getOne(int id) {
        return accountsRepository.getById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Account with id=%s not found", id)));
    }

    public Account update(int id, AccountEditDto accountEditDto) {
        Account account = accountsRepository.getById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Account with id=%s not found", id)));

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
