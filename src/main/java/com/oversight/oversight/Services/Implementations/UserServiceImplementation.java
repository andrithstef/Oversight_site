package com.oversight.oversight.Services.Implementations;

import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Persistence.Repositories.UserRepository;
import com.oversight.oversight.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByID(long id) {
        return userRepository.findByID(id);
    }

    @Override
    public User login(User user) {
        User exists= findByUsername(user.getUsername());
        if(exists != null){
            if(exists.getPassword().equals(user.getPassword())){
                return exists;
            }
        }
        return null;
    }

    @Override
    public void changePassword(User user, String password) {
        //remove user from database
        User old = userRepository.getById(user.getID());
        userRepository.delete(old);

        //set new password and save to database
        user.setPassword(password);
        userRepository.save(user);
    }
}
