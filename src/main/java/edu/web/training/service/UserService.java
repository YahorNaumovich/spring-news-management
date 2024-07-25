package edu.web.training.service;

import edu.web.training.entity.User;
import edu.web.training.entity.UserRole;
import edu.web.training.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    User authenticate(String username, String password) throws ServiceException;

    boolean usernameExists(String username) throws ServiceException;

    boolean emailExists(String email) throws ServiceException;

    void createUser(User newUser) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;

    List<UserRole> getAllRoles() throws ServiceException;

    void updateUserRole(int userId, int roleId) throws ServiceException;
}
