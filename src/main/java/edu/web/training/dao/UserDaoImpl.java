package edu.web.training.dao;

import edu.web.training.entity.User;
import jakarta.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

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
}
