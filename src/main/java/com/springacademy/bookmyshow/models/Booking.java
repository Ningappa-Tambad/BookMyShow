package com.springacademy.bookmyshow.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity(name="bookings")
public class Booking extends BaseModel {

    @ManyToOne
    private User user;

    @OneToMany
    @JoinColumn(name="bookingid")
    private List<ShowSeat> showSeat;


    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    private int amount;

    @OneToMany
    @JoinColumn(name="bookingid")
    private List<Payment> payments;

    private Date bookedAt;
}

/*
1              1
Booking -------User >>M:1
 m              1

 1                   M
 Booking            ShowSeat>>1:M
`   1                   1

1                      M
Booking              Payment   >>1:M
 1                      1

 */
