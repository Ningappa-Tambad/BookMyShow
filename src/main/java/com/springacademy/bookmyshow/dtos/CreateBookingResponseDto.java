package com.springacademy.bookmyshow.dtos;


import com.springacademy.bookmyshow.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingResponseDto {

private Booking booking;
private ResponseStatus responseStatus;

}
