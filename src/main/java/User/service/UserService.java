package User.service;

import User.model.User;
import User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    public List<User> getAllusers() {
        return userRepository.findAll();
    }



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
          return null;
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        // Set the user's password to the hashed password.
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return ResponseEntity.ok("ok");
    }


    public ResponseEntity<String> login(String username, String password){
        System.out.println("username: " + username +  " password: " + password);
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        return ResponseEntity.ok("ok");

    }



    }
