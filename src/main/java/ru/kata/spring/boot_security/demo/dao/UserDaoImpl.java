package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT a FROM User a", User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery("Select u from User u WHERE u.username = :username", User.class).setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public void detete(Long id) {
        entityManager.createQuery("DELETE FROM User u where u.id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
