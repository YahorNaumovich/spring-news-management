package edu.web.training.dao;

import edu.web.training.entity.User;
import edu.web.training.entity.UserRole;
import edu.web.training.exception.DaoException;

import java.util.List;

public interface UserDao {

    User findByUsername(String username) throws DaoException;

    User findByEmail(String email) throws DaoException;

    void save(User user) throws DaoException;

    List<User> findAllUsers() throws DaoException;

    List<UserRole> findAllRoles() throws DaoException;

    void updateUserRole(int userId, int roleId) throws DaoException;
}
