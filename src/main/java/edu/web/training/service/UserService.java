package edu.web.training.service;

import edu.web.training.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    User authenticate(String username, String password);
}
