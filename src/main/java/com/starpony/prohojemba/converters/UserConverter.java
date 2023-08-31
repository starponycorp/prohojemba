package com.starpony.prohojemba.converters;


import com.starpony.prohojemba.models.Account;
import com.starpony.prohojemba.dto.UserCurrentDto;

public class UserConverter {
    public static UserCurrentDto mapToCurrentUserDto(Account account) {
        UserCurrentDto userDto = new UserCurrentDto();
        userDto.setId(account.getId());
        userDto.setEmail(account.getEmail());
        userDto.setUsername(account.getUsername());
        userDto.setAvatar(account.getAvatar());

        return userDto;
    }
}
