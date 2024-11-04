package com.springacademy.bookmyshow.controllers;


import com.springacademy.bookmyshow.dtos.LoginRequestDto;
import com.springacademy.bookmyshow.dtos.ResponseStatus;
import com.springacademy.bookmyshow.dtos.UserSignUpRequestDto;
import com.springacademy.bookmyshow.dtos.UserSignUpResponseDto;
import com.springacademy.bookmyshow.exceptions.UserNotFoundException;
import com.springacademy.bookmyshow.models.User;
import com.springacademy.bookmyshow.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

@PostMapping("/signup")
    public UserSignUpResponseDto signUp(@RequestBody UserSignUpRequestDto requestDto)
    {

        User user= userService.signUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        UserSignUpResponseDto responseDto = new UserSignUpResponseDto();
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        responseDto.setName(responseDto.getName());
        responseDto.setEmail(responseDto.getEmail());

        return responseDto;
    }

    @GetMapping("/login")
    public ResponseStatus login(@RequestBody LoginRequestDto requestDto) throws UserNotFoundException {
        return userService.Login(requestDto.getEmail(),
                requestDto.getPassword());
    }

}
