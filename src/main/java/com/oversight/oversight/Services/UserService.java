package com.oversight.oversight.Services;

import com.oversight.oversight.Persistence.Entities.User;

public interface UserService {
    User save(User user);
    void delete(User user);

    User findByUsername(String username);
    User changePassword(User user, String password);

    String get_SHA_512(String passwordToHash);

    User findByID(long id);
}
