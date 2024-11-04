package com.springacademy.bookmyshow.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="screens")
public class Screen extends BaseModel
{
    private String name;

    @OneToMany
    @JoinColumn(name="screen_id")
    private List<Seat> seats;

    @ElementCollection
    private List<Feature> features;

//    @ManyToOne
//    private Theatre theatre;

}

/*
     1          M
   Screen      Seat   >>1:M
   1            1



 */
