package com.printerscheduler.server.service;

import com.printerscheduler.server.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface UserService {
    User create(User user);
    List<User> findAll();
    List<User> findUsersByRole(String role);
    User findById(Long id);
    User findByEmail(String email);
    User save(User user);
    void deleteById(Long id);
    void approveAccount(Long id, User currentUser) throws AccessDeniedException;
    void rejectAccount(Long id, User currentUser) throws AccessDeniedException;
    boolean authenticateUser(String email, String rawPassword);
    User findByEmailPassword(String username, String password);
    User getCurrentUser(UserDetails userDetails);
}
