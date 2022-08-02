package com.example.city.service.imp;

import com.example.city.model.Role;
import com.example.city.model.User;
import com.example.city.repository.UserRepository;
import com.example.city.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Service class for managing users.
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user, Role role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> authorities = new HashSet<>();
        authorities.add(role);
        user.setRoles(authorities);
        userRepository.save(user);
        log.info("Created Information for User: {}", user);
        return user;
    }
}
