package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;

public interface RoleDao {
    Collection<Role> findAll();

    Role findRoleById(Long id);

    public Collection<Role> makingListOfRolesByArray(String[] arr);
}
