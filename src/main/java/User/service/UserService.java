package User.service;

import User.model.User;
import User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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



    public List<User> getAllusers() {
        return userRepository.findAll();
    }



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
          return null;
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        // Set the user's password to the hashed password.
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }


    public User login(String username, String password){
        String hashedPassword = passwordEncoder.encode(password);
        User user = userRepository.findByUsernameAndPassword(username, hashedPassword);

        return user;

    }



    }
