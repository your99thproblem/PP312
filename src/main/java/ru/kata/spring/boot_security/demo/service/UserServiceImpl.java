package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    public User findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Transactional
    public List<User> selectAllUsers() {
        return userDao.findAll();
    }

    @Transactional
    public void saveUser(User user) {
        userDao.save(user);

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


    @Override
    @Transactional
    public void deleteRolesOfUser(Long id) {
        User user = userDao.findById(id);
        user.getUserRoles().clear();
        System.out.println("check");
        userDao.update(user);
    }

}
