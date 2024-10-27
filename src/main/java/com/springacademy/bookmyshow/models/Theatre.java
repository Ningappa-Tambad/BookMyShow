package com.springacademy.bookmyshow.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel {

    private String theatre_name;
    private String theatre_location;

    @OneToMany
    private List<Screen> screens;


}
/*


1           M
Theatre    Screen   >>1:M

 1            1
 */


