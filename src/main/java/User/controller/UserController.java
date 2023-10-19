package User.controller;


import User.model.User;
import User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("users")
public class UserController {


    @PostMapping("/register")
    public User register(@RequestBody User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        return userService.register(user);
    }

    @Autowired
    private UserService userService;

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//        return userService.login(user.getUsername(), user.getPassword());
//
//    }
    @GetMapping(path = "/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }



}
