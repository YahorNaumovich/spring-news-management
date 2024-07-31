package edu.web.training.service.impl;

import edu.web.training.dao.UserDao;
import edu.web.training.entity.User;
import edu.web.training.entity.UserRole;
import edu.web.training.exception.DaoException;
import edu.web.training.exception.ServiceException;
import edu.web.training.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User authenticate(String username, String password) throws ServiceException {

        logger.info("Authenticating user with username: {}", username);

        try {

            User user = userDao.getUserByUsername(username);

            if (user != null && user.getPassword().equals(password)) {

                logger.debug("User authenticated successfully: {}", username);
                return user;

            }

            logger.warn("Authentication failed for username: {}", username);
            return null;

        } catch (DaoException e) {

            logger.error("Error authenticating user with username: {}", username, e);
            throw new ServiceException("Failed to authenticate user with username: " + username, e);

        }
    }

    @Override
    public boolean usernameExists(String username) throws ServiceException {

        logger.info("Checking if username exists: {}", username);

        try {

            boolean exists = userDao.getUserByUsername(username) != null;
            logger.debug("Username {} exists: {}", username, exists);

            return exists;

        } catch (DaoException e) {

            logger.error("Error checking existence of username: {}", username, e);
            throw new ServiceException("Failed to check existence of username: " + username, e);

        }
    }

    @Override
    public boolean emailExists(String email) throws ServiceException {

        logger.info("Checking if email exists: {}", email);

        try {

            boolean exists = userDao.getUserByEmail(email) != null;
            logger.debug("Email {} exists: {}", email, exists);

            return exists;

        } catch (DaoException e) {

            logger.error("Error checking existence of email: {}", email, e);
            throw new ServiceException("Failed to check existence of email: " + email, e);

        }
    }

    @Override
    public void createUser(User user) throws ServiceException {

        logger.info("Creating user with username: {}", user.getUsername());

        try {

            userDao.saveUser(user);
            logger.debug("User created successfully: {}", user.getUsername());

        } catch (DaoException e) {

            logger.error("Error creating user with username: {}", user.getUsername(), e);
            throw new ServiceException("Failed to create user with username: " + user.getUsername(), e);

        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {

        logger.info("Fetching all users");

        try {

            List<User> users = userDao.getAllUsers();
            logger.debug("Retrieved {} users", users.size());

            return users;

        } catch (DaoException e) {

            logger.error("Error fetching all users", e);
            throw new ServiceException("Failed to retrieve all users", e);

        }
    }

    @Override
    public List<UserRole> getAllRoles() throws ServiceException {

        logger.info("Fetching all user roles");

        try {

            List<UserRole> roles = userDao.getAllRoles();
            logger.debug("Retrieved {} user roles", roles.size());

            return roles;

        } catch (DaoException e) {

            logger.error("Error fetching all user roles", e);
            throw new ServiceException("Failed to retrieve all user roles", e);

        }
    }

    @Override
    public void updateUserRole(int userId, int roleId) throws ServiceException {

        logger.info("Updating user role for user ID: {} to role ID: {}", userId, roleId);

        try {

            userDao.updateUserRole(userId, roleId);
            logger.debug("User role updated successfully for user ID: {} to role ID: {}", userId, roleId);

        } catch (DaoException e) {

            logger.error("Error updating user role for user ID: {} with role ID: {}", userId, roleId, e);
            throw new ServiceException("Failed to update user role for user ID: " + userId + " with role ID: " + roleId, e);

        }
    }

}
