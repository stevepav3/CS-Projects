package com.printerscheduler.server.controller;

import com.printerscheduler.server.model.Printer;
import com.printerscheduler.server.model.Reservation;
import com.printerscheduler.server.model.ReservationRequest;
import com.printerscheduler.server.service.ReservationService;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest resRequest) {

        LocalDateTime startTime = LocalDateTime.parse(resRequest.getStartDateTime(), DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endTime = LocalDateTime.parse(resRequest.getEndDateTime(), DateTimeFormatter.ISO_DATE_TIME);

        Reservation newReservation = reservationService.create(startTime, endTime,
                                                                resRequest.getPrinter(), resRequest.getUserEmail());

        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.findAll();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) {
        Reservation reservation = reservationService.findById(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping("/find/user-id/{id}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable("id") Long id) {
        List<Reservation> reservations = reservationService.findByUserId(id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/find/printer-id/{id}")
    public ResponseEntity<List<Reservation>> getReservationsByPrinterId(@PathVariable("id") Long id) {
        List<Reservation> reservations = reservationService.findByPrinterId(id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/find-available-printers")
    public ResponseEntity<List<Printer>> getAvailablePrinters(@RequestParam String startDateTime, @RequestParam String endDateTime) {
        return new ResponseEntity<>(reservationService.getAvailablePrinters(startDateTime, endDateTime), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long id, @RequestBody Reservation reservation) {
        reservation.setId(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable("id") Long id) {
        reservationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
