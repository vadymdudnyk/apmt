package com.vdudnyk.appointmentengine.salon;

import com.vdudnyk.appointmentengine.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Salon {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private User owner;

    @ManyToMany
    private List<User> workers;

}
