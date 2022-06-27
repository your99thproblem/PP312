package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;

public interface RoleDao {
    void save(Role role);
    Role update(Role role);
    Collection<Role> findAll();

    Role findRoleById(Long id);

    Role findByRoleName(String role);
    void detete(Long id);
    public Collection<Role> makingListOfRolesByArray(String[] arr);
}
