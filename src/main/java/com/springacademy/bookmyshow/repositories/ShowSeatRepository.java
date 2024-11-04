package com.springacademy.bookmyshow.repositories;

import com.springacademy.bookmyshow.models.Show;
import com.springacademy.bookmyshow.models.ShowSeat;
import com.springacademy.bookmyshow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {


    //Select * from show_seats where ID in(1,2,3,4);


    @Override
    List<ShowSeat> findAllById(Iterable<Long> showSeatIds);

    @Override
    ShowSeat save(ShowSeat showSeat);


}
