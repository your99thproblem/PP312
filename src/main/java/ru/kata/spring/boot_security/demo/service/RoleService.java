package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface RoleService {
    public List<Role> selectAllRoles();
    public Role findRoleById(Long userId);
    public User addRolesToUser(User user, String[] arr);
}
