package com.starpony.prohojemba.permissions;

import com.starpony.prohojemba.models.Permission;

import java.util.List;
import java.util.Optional;


public interface PermissionRepository {
    List<Permission> findAll();

    Optional<Permission> findOne(int id);

    void create(Permission permission);

    void update(Permission permission);

    void delete(int id);
}
