package userservice.service;

import userservice.model.User;
import userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return null;
        }
        else if(userRepository.existsByEmail(user.getEmail())) {
            return null;
        }
        //Set the user's password to the hashed password.
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public boolean login(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
        catch(Exception e) {
            return false;
        }

    }


}
