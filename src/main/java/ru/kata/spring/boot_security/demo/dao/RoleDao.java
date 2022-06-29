package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;

public interface RoleDao {
    public List<Role> findAll();

    public Role findRoleById(Long id);

    public User addRolesByIds(User user, String[] arr);
}
