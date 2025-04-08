package org.example.introspringboot.service;

import org.example.introspringboot.entity.User;
import org.example.introspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {


    User findByEmail(String username);

    User createUser (User user);
}
