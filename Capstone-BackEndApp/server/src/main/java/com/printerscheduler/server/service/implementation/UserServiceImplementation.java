package com.printerscheduler.server.service.implementation;

import com.printerscheduler.server.model.Role;
import com.printerscheduler.server.model.User;
import com.printerscheduler.server.repo.UserRepo;
import com.printerscheduler.server.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User create(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepo.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> findUsersByRole(String role) {
        List<User> users = new ArrayList<>();

        for(User i : userRepo.findAll()) {
            if(i.getRole() == Role.valueOf(role)) {
                users.add(i);
            }
        }

        return users;
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email " + email));
    }

    @Override
    public User findByEmailPassword(String username, String password) {
        Optional<User> userOpt = this.userRepo.findByEmail(username);
        if(userOpt.isPresent()) {
            User user = userOpt.get();
            String savedPassword = user.getPassword();
            if(savedPassword.equals(password)) {
                return user;
            }
         }
        return null;
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void approveAccount(Long id, User currentUser) throws AccessDeniedException {
        if (currentUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("Only Administrators can access this feature.");

        }
        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));

        user.setApproved(true);
        userRepo.save(user);
    }

    @Override
    public void rejectAccount(Long id, User currentUser) throws AccessDeniedException {
        if (currentUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("Only Administrators can access this feature.");

        }
        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));

        userRepo.delete(user);
    }

    @Override
    public boolean authenticateUser(String email, String rawPassword) {
        // Retrieve the user from the database using the email
        Optional<User> userOptional = userRepo.findByEmail(email);

        if (!userOptional.isPresent()) {
            // No user found with the provided email, return false
            return false;
        }

        User user = userOptional.get();

        // Check if raw password matches the hashed password stored in database
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    @Override
    public User getCurrentUser(UserDetails userDetails) {
        if (userDetails == null) {
            throw new EntityNotFoundException("User not found");
        }

        String username = userDetails.getUsername();
        return userRepo.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
