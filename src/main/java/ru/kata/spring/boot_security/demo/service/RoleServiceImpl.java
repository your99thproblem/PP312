package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleDao roleDao;
    @Override
    @Transactional
    public List<Role> selectAllRoles() {
        return roleDao.findAll();
    }

    @Override
    @Transactional
    public Role findRoleById(Long roleId) {
        return roleDao.findRoleById(roleId);
    }
    @Override
    @Transactional
    public User addRolesToUser(User user, String[] arr) {
        return roleDao.addRolesByIds(user, arr);

    }


}
