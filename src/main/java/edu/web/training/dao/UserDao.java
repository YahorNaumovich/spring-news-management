package edu.web.training.dao;

import edu.web.training.entity.User;
import edu.web.training.entity.UserRole;
import edu.web.training.exception.DaoException;

import java.util.List;

public interface UserDao {

    User getUserByUsername(String username) throws DaoException;

    User getUserByEmail(String email) throws DaoException;

    void saveUser(User user) throws DaoException;

    List<User> getAllUsers() throws DaoException;

    List<UserRole> getAllRoles() throws DaoException;

    void updateUserRole(int userId, int roleId) throws DaoException;
}
