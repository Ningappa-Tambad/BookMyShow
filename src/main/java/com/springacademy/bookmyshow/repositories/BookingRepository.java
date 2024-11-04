package com.springacademy.bookmyshow.repositories;

import com.springacademy.bookmyshow.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {


    Booking save(Booking booking);
}
