package com.starpony.prohojemba.converters;


import com.starpony.prohojemba.models.User;
import com.starpony.prohojemba.dto.UserCurrentDto;

public class UserConverter {
    public static UserCurrentDto mapToCurrentUserDto(User user) {
        UserCurrentDto userDto = new UserCurrentDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setAvatar(user.getAvatar());

        return userDto;
    }
}
