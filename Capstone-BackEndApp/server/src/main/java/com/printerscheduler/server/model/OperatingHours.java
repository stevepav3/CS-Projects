package com.printerscheduler.server.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Data
public class OperatingHours implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operating_hours_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalTime start;

    @Column(nullable = false)
    private LocalTime end;

    public OperatingHours() {
        this.start = LocalTime.of(9,0);
        this.end = LocalTime.of(17,0);
    }
}