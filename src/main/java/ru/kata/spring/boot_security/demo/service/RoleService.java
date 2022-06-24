package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;

public interface RoleService {
    public Collection<Role> selectAllRoles();
    public Role findRoleById(Long userId);
}
