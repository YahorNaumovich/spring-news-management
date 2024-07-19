package edu.web.training.dao.impl;

import edu.web.training.dao.UserDao;
import edu.web.training.entity.User;
import jakarta.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByUsername(String username) {
        try {
            return (User) sessionFactory.getCurrentSession()
                    .createQuery("from User where username = :username")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // or handle the exception as needed
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return (User) sessionFactory.getCurrentSession()
                    .createQuery("from User where email = :email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }
}
