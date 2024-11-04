package com.springacademy.bookmyshow.services;


import com.springacademy.bookmyshow.dtos.ResponseStatus;
import com.springacademy.bookmyshow.models.User;
import com.springacademy.bookmyshow.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User signUp(String name, String email, String password) {
        Optional<User> optionalUser = userRepository.findAllByEmail(email);

        if (optionalUser.isPresent()) {
            //User with given email id is already present in the data base
            //Navigate to the login page
//               User user = optionalUser.get();
//               user.setName(name);
//               user.setEmail(email);
//               user.setPassword(password);

        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);

        user.setPassword(bCryptPasswordEncoder.encode(password));


    /*
     //userRepository.save(user);

    Encoding+salting
    =Bcrypt password encoder
     */

        return userRepository.save(user);

    }

    public ResponseStatus Login(String email, String password) {
        Optional<User> optionalUser = userRepository.findAllByEmail(email);


        if (optionalUser.isEmpty()) {
            //Ask user to signup
        }

         User user = optionalUser.get();
        if(bCryptPasswordEncoder.matches(password,user.getPassword())) {
            return ResponseStatus.SUCCESS;
        }

        return ResponseStatus.FAILURE;

    }
}
