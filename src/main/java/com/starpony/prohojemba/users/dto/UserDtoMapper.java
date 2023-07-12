package com.starpony.prohojemba.users.dto;

import com.starpony.prohojemba.users.User;

public class UserDtoMapper {
    public static User mapToUser(EditProfileDto editProfileDto) {
        User user = new User();
        user.setUsername(editProfileDto.getUsername());
        user.setAvatar(editProfileDto.getAvatar());
        return user;
    }

    public CurrentUserDto mapToCurrentUserDto(User user) {
        CurrentUserDto currentUserDto = new CurrentUserDto();
        currentUserDto.setUsername(user.getUsername());
        currentUserDto.setAvatar(user.getAvatar());
        currentUserDto.setEmail(user.getEmail());
        return currentUserDto;

    }
}
