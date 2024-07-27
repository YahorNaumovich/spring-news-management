package edu.web.training.dao.impl;

import edu.web.training.dao.UserDao;
import edu.web.training.entity.User;
import edu.web.training.entity.UserRole;
import edu.web.training.exception.DaoException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUserByUsername(String username) throws DaoException {
        try {
            return (User) sessionFactory.getCurrentSession()
                    .createQuery("from User where username = :username")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DaoException("Failed to find user by username: " + username, e);
        }
    }

    @Override
    public User getUserByEmail(String email) throws DaoException {
        try {
            return (User) sessionFactory.getCurrentSession()
                    .createQuery("from User where email = :email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DaoException("Failed to find user by email: " + email, e);
        }
    }


    @Override
    public void saveUser(User user) throws DaoException {
        try {
            sessionFactory.getCurrentSession().persist(user);
        } catch (Exception e) {
            throw new DaoException("Failed to save user", e);
        }
    }

    @Override
    public List<User> getAllUsers() throws DaoException {
        try {
            return sessionFactory.getCurrentSession().createQuery("from User", User.class).getResultList();
        } catch (Exception e) {
            throw new DaoException("Failed to retrieve all users", e);
        }
    }

    @Override
    public List<UserRole> getAllRoles() throws DaoException {
        try {
            return sessionFactory.getCurrentSession().createQuery("from UserRole", UserRole.class).getResultList();
        } catch (Exception e) {
            throw new DaoException("Failed to retrieve all user roles", e);
        }
    }

    @Override
    public void updateUserRole(int userId, int roleId) throws DaoException {
        try {
            User user = sessionFactory.getCurrentSession().get(User.class, userId);
            UserRole userRole = sessionFactory.getCurrentSession().get(UserRole.class, roleId);

            if (user != null && userRole != null) {
                user.setUserRole(userRole);
                sessionFactory.getCurrentSession().merge(user);
            } else {
                throw new DaoException("User or UserRole not found for userId: " + userId + " or roleId: " + roleId);
            }
        } catch (Exception e) {
            throw new DaoException("Failed to update user role for userId: " + userId + " and roleId: " + roleId, e);
        }
    }

}
