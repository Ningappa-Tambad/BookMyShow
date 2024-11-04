package com.springacademy.bookmyshow.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name="shows")
public class Show extends BaseModel{


    @ManyToOne
     private Movie movie;
     private Date startTime;
     private Date endTime;

     @ManyToOne
     private Screen screen;

     @ElementCollection
     private List<Feature> features;

}


/*
1                       1
Show                   Movie >>M:1
 M                       1

1                1
Show           Screen   >M:1
M                1




 */