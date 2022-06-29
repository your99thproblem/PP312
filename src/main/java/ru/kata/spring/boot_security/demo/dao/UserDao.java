package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    public void save(User user, String[] roles);
    User update(User user);
    List<User> findAll();
    User findById(Long id);
    void detete(Long id);
    public User getUserByNameWithRoles(String username);
}
