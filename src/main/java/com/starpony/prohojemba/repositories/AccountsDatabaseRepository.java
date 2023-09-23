package com.starpony.prohojemba.repositories;

import com.starpony.prohojemba.mappers.AccountMapper;
import com.starpony.prohojemba.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class AccountsDatabaseRepository {
    private final AccountMapper accountMapper;

    @Autowired
    public AccountsDatabaseRepository(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public Optional<Account> getById(int id) {
        return Optional.ofNullable(accountMapper.selectById(id));
    }

    public Optional<Account> getByEmail(String email) {
        return Optional.ofNullable(accountMapper.selectByEmail(email));
    }

    public void update(Account account) {
        accountMapper.update(account);
    }
}
