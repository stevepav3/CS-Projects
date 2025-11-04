package com.printerscheduler.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data                   // Generate Getters/Setters & More
@NoArgsConstructor      // Generate No Arg Constructor
@AllArgsConstructor     // Generate All Args Constructor
public class Reservation implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "reservation_id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;

    // Define many-to-one relationship between reservation and printer
    @ManyToOne
    @JoinColumn(name = "printer_id", nullable = false)
    private Printer printer;

    // Define many-to-one relationship between reservation and user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    public Reservation(LocalDateTime startTime, LocalDateTime endTime, Printer printer, User user, Location location) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.printer = printer;
        this.user = user;
        this.location = location;
    }

    // Checks if pending reservation has conflicting times with "this" reservation
    public boolean isConflicting(LocalDateTime start, LocalDateTime end) {
        // Ensure start and end times are aligned to one-hour blocks
        if (start.getMinute() != 0 || end.getMinute() != 0 || end.isBefore(start) || start.plusHours(1).isAfter(end)) {
            throw new IllegalArgumentException("Invalid reservation times. Start and end times must be in one-hour blocks.");
        }

        if(!start.toLocalDate().equals(end.toLocalDate())) {
            throw new IllegalArgumentException("Invalid reservation times. Start and end times must be on the same day.");
        }

        return ((start.isEqual(this.startTime) || start.isAfter(this.startTime)) && start.isBefore(this.endTime))
                || (end.isAfter(this.startTime) && end.isBefore(this.endTime))
                || (start.isBefore(this.startTime) && end.isAfter(this.endTime));
    }

}
