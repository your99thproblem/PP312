package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private RoleDao roleDao;

    @Override
    public void save(User user, String[] roles) {
        for (String ids : roles) {
            user.addRole(roleDao.findRoleById(Long.valueOf(ids)));
        }
        entityManager.persist(user);
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("Select u from User u ", User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void detete(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public User getUserByNameWithRoles(String username) {
        User user = entityManager.createQuery("Select u from User u " +
                        "join fetch u.listRoles lr " +
                        "join fetch lr.role " +
                        "WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        return user;
    }


}
