package com.starpony.prohojemba.converters;

import com.starpony.prohojemba.models.Permission;
import com.starpony.prohojemba.dto.PermissionDto;

public class PermissionConverter {
    public static PermissionDto mapToPermissionDto(Permission permission) {
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setId(permission.getId());
        permissionDto.setSystemName(permission.getSystemName());
        permissionDto.setViewName(permission.getViewName());

        return permissionDto;
    }
}
