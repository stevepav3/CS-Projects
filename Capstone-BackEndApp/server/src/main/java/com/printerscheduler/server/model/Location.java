package com.printerscheduler.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Location {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "location_id", nullable = false)
        private Long id;

        @Column(nullable = false)
        private String locationName;

        public Location() {}

        public Location(String locationName) {
                this.locationName = locationName;
        }
}
