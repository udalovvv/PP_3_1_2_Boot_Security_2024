package ru.kata.spring.boot_security.demo.service;



import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    public void save(User user);

    public User findById(long id);

    public void updateUser(long id, User updatedUser);

    public void deleteById(long id);

    public UserDetails findByEmail(String email);

}
