package com.starpony.prohojemba.permissions;

import com.starpony.prohojemba.models.Permission;
import com.starpony.prohojemba.permissions.exceptions.PermissionAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class PermissionDbRepository implements PermissionRepository{
    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionDbRepository(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<Permission> findAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public Optional<Permission> findOne(int id) {
        return Optional.ofNullable(permissionMapper.selectById(id));
    }

    @Override
    public void create(Permission permission) {
        Permission permissionForUniqueCheck =
                permissionMapper.selectBySystemNameOrViewName(permission.getSystemName(), permission.getViewName());
        if (permissionForUniqueCheck != null) {
            if (permissionForUniqueCheck.getSystemName().equals(permission.getSystemName()))
                throw new PermissionAlreadyExistsException(
                        String.format("Permission with systemName=%s already exists", permissionForUniqueCheck.getSystemName())
                );
            if (permissionForUniqueCheck.getViewName().equals(permission.getViewName()))
                throw new PermissionAlreadyExistsException(
                        String.format("Permission with viewName=%s already exists", permissionForUniqueCheck.getViewName())
                );
        }

        permissionMapper.create(permission);
    }

    @Override
    public void update(Permission permission) {
        Permission permissionForUniqueCheck =
                permissionMapper.selectBySystemNameOrViewName(permission.getSystemName(), permission.getViewName());
        if (permissionForUniqueCheck != null && permissionForUniqueCheck.getId() != permission.getId()) {
            if (permissionForUniqueCheck.getSystemName().equals(permission.getSystemName()))
                throw new PermissionAlreadyExistsException(
                        String.format("Permission with systemName=%s already exists", permissionForUniqueCheck.getSystemName())
                );
            if (permissionForUniqueCheck.getViewName().equals(permission.getViewName()))
                throw new PermissionAlreadyExistsException(
                        String.format("Permission with viewName=%s already exists", permissionForUniqueCheck.getViewName())
                );
        }

        permissionMapper.update(permission);
    }

    @Override
    public void delete(int id) {
        permissionMapper.delete(id);
    }
}
