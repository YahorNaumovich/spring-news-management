package edu.web.training.dao;

import edu.web.training.entity.User;
import edu.web.training.entity.UserRole;

import java.util.List;

public interface UserDao {

    User findByUsername(String username);

    User findByEmail(String email);

    void save(User user);

    List<User> findAllUsers();

    List<UserRole> findAllRoles();

}
