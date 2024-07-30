package edu.web.training.service.impl;

import edu.web.training.dao.UserDao;
import edu.web.training.entity.User;
import edu.web.training.entity.UserRole;
import edu.web.training.exception.DaoException;
import edu.web.training.exception.ServiceException;
import edu.web.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User authenticate(String username, String password) throws ServiceException {

        try {

            User user = userDao.getUserByUsername(username);

            if (user != null && user.getPassword().equals(password)) {
                return user;
            }

            return null;

        } catch (DaoException e) {

            throw new ServiceException("Failed to authenticate user with username: " + username, e);

        }
    }

    @Override
    public boolean usernameExists(String username) throws ServiceException {

        try {

            return userDao.getUserByUsername(username) != null;

        } catch (DaoException e) {

            throw new ServiceException("Failed to check existence of username: " + username, e);

        }
    }

    @Override
    public boolean emailExists(String email) throws ServiceException {

        try {

            return userDao.getUserByEmail(email) != null;

        } catch (DaoException e) {

            throw new ServiceException("Failed to check existence of email: " + email, e);

        }
    }

    @Override
    public void createUser(User user) throws ServiceException {

        try {

            userDao.saveUser(user);

        } catch (DaoException e) {

            throw new ServiceException("Failed to create user with username: " + user.getUsername(), e);

        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {

        try {

            return userDao.getAllUsers();

        } catch (DaoException e) {

            throw new ServiceException("Failed to retrieve all users", e);

        }
    }

    @Override
    public List<UserRole> getAllRoles() throws ServiceException {

        try {

            return userDao.getAllRoles();

        } catch (DaoException e) {

            throw new ServiceException("Failed to retrieve all user roles", e);

        }
    }

    @Override
    public void updateUserRole(int userId, int roleId) throws ServiceException {

        try {

            userDao.updateUserRole(userId, roleId);

        } catch (DaoException e) {

            throw new ServiceException("Failed to update user role for user ID: " + userId + " with role ID: " + roleId, e);

        }
    }

}
