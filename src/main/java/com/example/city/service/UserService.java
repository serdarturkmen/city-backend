package com.example.city.service;

import com.example.city.model.Role;
import com.example.city.model.User;

public interface UserService {

    User createUser(User user, Role role);

}
