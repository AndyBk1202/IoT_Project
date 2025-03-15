package com.andy.iot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer id;
    private boolean mon;
    private boolean tue;
    private boolean wed;
    private boolean thu;
    private boolean fri;
    private boolean sat;
    private boolean sun;
    @Column(name = "from_time")
    private LocalDateTime from;
    @Column(name = "to_time")
    private LocalDateTime to;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

}
