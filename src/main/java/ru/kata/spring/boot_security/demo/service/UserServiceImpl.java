package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;


    @Transactional
    public User findUserById(Long userId) {
        return userDao.findById(userId);
    }

    @Transactional
    public User getUserByUsernameWithRoles(String username) {
        return userDao.getUserByNameWithRoles(username);
    }

    @Transactional
    public List<User> selectAllUsers() {
        return userDao.findAll();
    }

    @Transactional
    public void saveUser(User user, String[] roles) {
        userDao.save(user, roles);
    }

    @Transactional
    public void delete(Long id) {
        userDao.detete(id);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }


}
