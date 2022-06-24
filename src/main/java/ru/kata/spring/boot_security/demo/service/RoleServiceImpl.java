package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleDao roleDao;
    @Override
    @Transactional
    public Collection<Role> selectAllRoles() {
        return roleDao.findAll();
    }

    @Override
    public Role findRoleById(Long roleId) {
        return roleDao.findRoleById(roleId);
    }


}
