package com.printerscheduler.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationRequest {

    private String startDateTime;
    private String endDateTime;
    private Printer printer;
    private String userEmail;
}
