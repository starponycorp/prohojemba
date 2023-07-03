package com.starpony.prohojemba.permissions.dto;

import java.util.List;


public class PermissionListDto {
    private List<PermissionDto> items;

    public List<PermissionDto> getItems() {
        return items;
    }

    public void setItems(List<PermissionDto> items) {
        this.items = items;
    }
}
