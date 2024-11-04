package com.springacademy.bookmyshow.controllers;


import com.springacademy.bookmyshow.dtos.CreateBookingRequestDto;
import com.springacademy.bookmyshow.dtos.CreateBookingResponseDto;
import com.springacademy.bookmyshow.dtos.ResponseStatus;
import com.springacademy.bookmyshow.exceptions.ShowSeatNotFoundException;
import com.springacademy.bookmyshow.exceptions.UserNotFoundException;
import com.springacademy.bookmyshow.models.Booking;
import com.springacademy.bookmyshow.services.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping

    public CreateBookingResponseDto createBooking(@RequestBody CreateBookingRequestDto requestDto) throws UserNotFoundException, ShowSeatNotFoundException {


        CreateBookingResponseDto responseDto = new CreateBookingResponseDto();
        Booking booking = bookingService.createBooking(requestDto.getBookingIds(),
                requestDto.getUserId());


        responseDto.setBooking(booking);
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);


        if(booking==null)
        {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
