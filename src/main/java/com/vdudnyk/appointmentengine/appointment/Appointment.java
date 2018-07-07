package com.vdudnyk.appointmentengine.appointment;

import com.vdudnyk.appointmentengine.salon.Salon;
import com.vdudnyk.appointmentengine.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Wither
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;

    @ManyToOne
    private User reservedBy;

    @ManyToOne
    private Salon salon;
}
