package edu.web.training.service.impl;

import edu.web.training.dao.UserDao;
import edu.web.training.entity.User;
import edu.web.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User authenticate(String username, String password) {

        User user = userDao.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {

            return user;

        }

        return null;
    }

    @Override
    public boolean usernameExists(String username) {
        return userDao.findByUsername(username) != null;
    }

    @Override
    public boolean emailExists(String email) {
        return userDao.findByEmail(email) != null;
    }

    @Override
    public void createUser(User user) {
        userDao.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
