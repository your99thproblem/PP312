package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    public User findUserById(Long userId);
    public List<User> selectAllUsers();
    public void saveUser(User user, String[] roles);
    public void delete(Long id);
    public void update(User user, String[] roles);
    public User getUserByUsernameWithRoles(String username);
}
