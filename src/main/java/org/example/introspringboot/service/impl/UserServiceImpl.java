package org.example.introspringboot.service.impl;


import org.example.introspringboot.entity.Enrollment;
import org.example.introspringboot.entity.User;
import org.example.introspringboot.repository.CourseRepository;
import org.example.introspringboot.repository.EnrrollmentRepository;
import org.example.introspringboot.repository.StudentRepository;
import org.example.introspringboot.repository.UserRepository;
import org.example.introspringboot.service.EnrrollmentService;
import org.example.introspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username).orElseThrow();
    }

    @Override
    public User createUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }


}



