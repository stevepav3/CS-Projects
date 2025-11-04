package com.printerscheduler.server.controller;

import com.printerscheduler.server.model.Role;
import com.printerscheduler.server.model.User;
import com.printerscheduler.server.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/current-user")
    public User getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getCurrentUser(userDetails);
    }

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        return ResponseEntity.ok().build();
    }



    public record UserResp(Long id, String firstName, String lastName, String email, int hoursReserved, boolean approved, Role role, int msgLimit) {
        public UserResp(User user) {
            this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getHoursReserved(),
            user.isApproved(), user.getRole(), user.getMsgLimit());
        }
    }

    public record LoginReq(String username, String password) {}

    @PostMapping("/login")
    public ResponseEntity<UserResp> login(@RequestBody LoginReq loginRequest) {
        if (userService.authenticateUser(loginRequest.username(), loginRequest.password())) {
            User user = userService.findByEmail(loginRequest.username());
            return ResponseEntity.ok(new UserResp(user));
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
}
