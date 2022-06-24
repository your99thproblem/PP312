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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getUserRoles()));
    }

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
    public User addRole(User user, Long id) {

            List<Role> list = new ArrayList<>();
            list.add(roleDao.findRoleById(id));
            user.setUserRoles(list);
        return user;
    }

    @Override
    @Transactional
    public void deleteRolesOfUser(Long id) {
        User user = userDao.findById(id);
        user.getUserRoles().clear();
        System.out.println("check");
        userDao.update(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }

}
