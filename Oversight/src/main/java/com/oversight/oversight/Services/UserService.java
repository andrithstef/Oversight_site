package com.oversight.oversight.Services;

import com.oversight.oversight.Persistence.Entities.User;

import java.util.List;

public interface UserService {
    User save(User user);
    void delete(User user);
    List<User> findAll();
    User findByUsername(String username);
    User findByID(long id);
    User login(User user);
    User changePassword(User user, String password);
    String get_SHA_512(String passwordToHash);
}
