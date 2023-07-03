package com.starpony.prohojemba.permissions.dto;

import com.starpony.prohojemba.permissions.Permission;

import java.util.List;
import java.util.stream.Collectors;


public class PermissionDtoMapper {
    public static Permission mapToPermission(EditPermissionDto permissionDto) {
        Permission permission = new Permission();
        permission.setSystemName(permissionDto.getSystemName());
        permission.setViewName(permissionDto.getViewName());
        return permission;
    }

    public static PermissionDto mapToPermissionDto(Permission permission) {
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setId(permissionDto.getId());
        permissionDto.setSystemName(permissionDto.getSystemName());
        permissionDto.setViewName(permission.getViewName());
        return permissionDto;
    }

    public static PermissionListDto mapToPermissionListDto(List<Permission> permissions) {
        PermissionListDto permissionListDto = new PermissionListDto();
        permissionListDto.setItems(permissions.stream().map(
                PermissionDtoMapper::mapToPermissionDto).collect(Collectors.toList()));
        return permissionListDto;
    }
}
