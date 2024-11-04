package com.springacademy.bookmyshow.services;


import com.springacademy.bookmyshow.exceptions.ShowSeatNotFoundException;
import com.springacademy.bookmyshow.exceptions.UserNotFoundException;
import com.springacademy.bookmyshow.models.Booking;
import com.springacademy.bookmyshow.models.ShowSeat;
import com.springacademy.bookmyshow.models.ShowSeatStatus;
import com.springacademy.bookmyshow.models.User;
import com.springacademy.bookmyshow.repositories.BookingRepository;
import com.springacademy.bookmyshow.repositories.ShowSeatRepository;
import com.springacademy.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService
{

    private UserRepository userRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;
    private BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository,
                          ShowSeatRepository showSeatRepository,
                          PriceCalculatorService priceCalculatorService,BookingRepository bookingRepository)
    {
        this.userRepository = userRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(List<Long> showSeatIds, Long userId) throws UserNotFoundException, ShowSeatNotFoundException {


     //   1.Fetch the user from userId
     //   2.If user not found, Then throw an exception
       Optional<User> optionalUser =userRepository.findById(userId);

       if(optionalUser.isEmpty())
       {
           throw new UserNotFoundException("User with"+userId+ "Not found");
       }

       User user = optionalUser.get();


       // 3.Fetch the showseat object from the databse

      List<ShowSeat> showSeats =showSeatRepository.findAllById(showSeatIds);


      for(ShowSeat showSeat : showSeats)
      {
          if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE))
          {
              throw new ShowSeatNotFoundException("Show seat with showId:" +showSeat.getShow().getId()+
                      "and Seatid:" +showSeat.getSeat().getId()+ "Is not available");
          }
      }

      List<ShowSeat> savedShowSeats = new ArrayList<>();

      for(ShowSeat showSeat : showSeats)
      {
          showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);

          savedShowSeats.add(showSeatRepository.save(showSeat));
      }

      //Create the booking  object with status as pending

        Booking booking = new Booking();
        booking.setShowSeat(savedShowSeats);
        booking.setUser(user);
        booking.setAmount(priceCalculatorService.calculatePrice(savedShowSeats));



      //
       return bookingRepository.save(booking);
    }
}

 /*

       1.Fetch the user from userId
       2.If user not found, Then throw an exception
       3.Fetch the showseat object from the databse
       4.Check if showseats are available
       5.If not available-Throw an exception
              ,.......TAKE A LOCK.................

       6.Check the showseat status again
       7.Change showseat status to blocked

       .........RELEASE LOCK.........
       8.Create the booking with pending status
       9.Move to the payment page
        */


