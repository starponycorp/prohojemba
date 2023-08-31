package com.starpony.prohojemba.repositories;

import com.starpony.prohojemba.mappers.AccountMapper;
import com.starpony.prohojemba.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class UsersDatabaseRepository {
    private final AccountMapper accountMapper;

    @Autowired
    public UsersDatabaseRepository(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public Optional<Account> getById(int id) {
        return Optional.ofNullable(accountMapper.selectById(id));
    }
}
