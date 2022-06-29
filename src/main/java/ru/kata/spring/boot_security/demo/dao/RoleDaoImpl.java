package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("SELECT a FROM Role a", Role.class).getResultList();
    }

    @Override
    public Role findRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }
}
