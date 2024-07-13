package edu.web.training.dao;

import edu.web.training.entity.User;

public interface UserDao {

    User findByUsername(String username);
}
