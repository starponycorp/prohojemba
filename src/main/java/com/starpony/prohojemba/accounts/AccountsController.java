package com.starpony.prohojemba.accounts;

import com.starpony.prohojemba.converters.UserConverter;
import com.starpony.prohojemba.dto.AccountEditDto;
import com.starpony.prohojemba.accounts.models.Account;
import com.starpony.prohojemba.dto.UserCurrentDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/users")
public class AccountsController {
    private final AccountsService accountsService;
    private final AuthService authService;

    @Autowired
    public AccountsController(AccountsService accountsService, AuthService authService) {
        this.accountsService = accountsService;
        this.authService = authService;
    }

    @RequestMapping(value = "/@me", method = RequestMethod.GET)
    public UserCurrentDto getCurrentUser() {
        Account account = authService.getAuthenticatedAccount();
        return UserConverter.mapToCurrentUserDto(accountsService.getOne(account.getId()));
    }

    @RequestMapping(value = "/@me", method = RequestMethod.PUT)
    public UserCurrentDto updateCurrentUser(@RequestBody AccountEditDto accountEditDto) {
        int currentUserId = 1; // TODO заменить на получение user id из токена
        return UserConverter.mapToCurrentUserDto(accountsService.update(currentUserId, accountEditDto));
    }
}
