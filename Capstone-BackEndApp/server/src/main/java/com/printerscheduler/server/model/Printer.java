package com.printerscheduler.server.model;

import jakarta.persistence.*;
import jakarta.transaction.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.status.StatusData;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data                   // Generate Getters/Setters & More
@NoArgsConstructor      // Generate No Arg Constructor
@AllArgsConstructor     // Generate All Args Constructor
public class Printer implements Serializable {
    // Generated id value for database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "printer_id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false)
    private boolean inUse;

    // Operating hours of printer
    @ManyToOne
    @JoinColumn(name = "operating_hours_id", nullable = false)
    private OperatingHours operatingHours;

    // Returns whether a reservation hours conflict with printer operating hours
    public boolean isConflicting(LocalDateTime reservationStart, LocalDateTime reservationEnd) {
        LocalTime resStart = reservationStart.toLocalTime();
        LocalTime resEnd = reservationEnd.toLocalTime();


        return (resStart.isAfter(operatingHours.getEnd()) || resEnd.isAfter(operatingHours.getEnd())
                || resStart.isBefore(operatingHours.getStart()) || resEnd.isBefore(operatingHours.getStart()));

    }
}