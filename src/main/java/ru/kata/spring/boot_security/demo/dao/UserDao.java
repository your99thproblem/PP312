package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    User update(User user);
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    void detete(Long id);
}
