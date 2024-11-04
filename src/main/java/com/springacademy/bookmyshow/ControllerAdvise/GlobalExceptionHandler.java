package com.springacademy.bookmyshow.ControllerAdvise;


import com.springacademy.bookmyshow.exceptions.ShowSeatNotFoundException;
import com.springacademy.bookmyshow.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFoundException()
    {
        System.out.println("User not found");
    }

    @ExceptionHandler(ShowSeatNotFoundException.class)
    public void handleShowSeatNotFoundException()
    {

    }
}
