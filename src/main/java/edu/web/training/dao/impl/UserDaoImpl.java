package edu.web.training.dao.impl;

import edu.web.training.dao.UserDao;
import edu.web.training.entity.User;
import edu.web.training.entity.UserRole;
import jakarta.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<User> findAllUsers() {

        return sessionFactory.getCurrentSession().createQuery("from User", User.class).getResultList();
    }

    @Override
    public List<UserRole> findAllRoles() {

        return sessionFactory.getCurrentSession().createQuery("from UserRole", UserRole.class).getResultList();
    }

}
