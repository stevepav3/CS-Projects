package com.printerscheduler.server.service;

import com.printerscheduler.server.model.Printer;
import com.printerscheduler.server.model.Reservation;
import com.printerscheduler.server.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ReservationService {
    Reservation create(LocalDateTime startDateTime, LocalDateTime endDateTime, Printer printer, String userEmail);
    List<Reservation> findAll();
    List<Reservation> findByPrinterId(Long id);
    List<Reservation> findByUserId(Long id);
    Reservation findById(Long id);
    Reservation save(Reservation reservation);
    void deleteById(Long id);
    List<Printer> getAvailablePrinters(String startDateTime, String endDateTime);
    public boolean isWithinLimits(LocalDateTime start, LocalDateTime end, User user);
}
