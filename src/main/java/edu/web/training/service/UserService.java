package edu.web.training.service;

import edu.web.training.entity.User;
import edu.web.training.entity.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    User authenticate(String username, String password);

    boolean usernameExists(String username);

    boolean emailExists(String email);

    void createUser(User newUser);

    List<User> getAllUsers();

    List<UserRole> getAllRoles();

}
