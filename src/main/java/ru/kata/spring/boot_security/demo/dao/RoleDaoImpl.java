package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Collection<Role> findAll() {
        return entityManager.createQuery("SELECT a FROM Role a", Role.class).getResultList();
    }

    @Override
    public Role findRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }


    @Override
    public Collection<Role> makingListOfRolesByArray(String[] arr) {
        List<Role> list = new ArrayList<>();
        for (String id : arr) {
            list.add(entityManager.createQuery("select u from Role u where u.id = :role", Role.class).setParameter("role", Long.valueOf(id)).getSingleResult());
        }
        return list;
    }
}
