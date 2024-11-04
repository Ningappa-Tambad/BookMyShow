package com.springacademy.bookmyshow.services;


import com.springacademy.bookmyshow.models.ShowSeat;
import com.springacademy.bookmyshow.models.ShowSeatType;
import com.springacademy.bookmyshow.repositories.ShowSeatRepository;
import com.springacademy.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class PriceCalculatorService {

    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository)
    {
        this.showSeatTypeRepository = showSeatTypeRepository;

    }

    public int calculatePrice(List<ShowSeat> showSeats) {

        int amount=0;

          List<ShowSeatType> showSeatTypes =showSeatTypeRepository.findAllByShow(showSeats.get(0).getShow());


          for (ShowSeat showSeat : showSeats) {

                 for(ShowSeatType showSeattype: showSeatTypes)
                 {
                    if(showSeat.getSeat().getSeatType().equals(showSeattype.getSeatType()))
                    {
                        amount+=showSeattype.getPrice();
                        break;
                    }
                 }
          }


        return amount;
    }
}
