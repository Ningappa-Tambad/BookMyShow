package com.springacademy.bookmyshow.repositories;

import com.springacademy.bookmyshow.models.Show;
import com.springacademy.bookmyshow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType,Long>
{
    //Select * from show_seat_type where id=123;

    /*

    123-showid GOLD 500
    123-SILVER 300
    123 platinum 700
     */



    List<ShowSeatType> findAllByShow(Show show);
}
