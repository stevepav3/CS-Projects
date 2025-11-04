package com.printerscheduler.server.service.implementation;

import com.printerscheduler.server.model.Location;
import com.printerscheduler.server.model.Printer;
import com.printerscheduler.server.model.Reservation;
import com.printerscheduler.server.model.User;
import com.printerscheduler.server.repo.PrinterRepo;
import com.printerscheduler.server.repo.ReservationRepo;
import com.printerscheduler.server.service.ReservationService;
import com.printerscheduler.server.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

@Service
public class ReservationServiceImplementation implements ReservationService {

    private final ReservationRepo reservationRepo;
    private final PrinterRepo printerRepo;
    private final UserService userService;

    @Autowired
    public ReservationServiceImplementation(ReservationRepo reservationRepo, PrinterRepo printerRepo, UserService userService) {
        this.reservationRepo = reservationRepo;
        this.printerRepo = printerRepo;
        this.userService = userService;
    }

    @Override
    public Reservation create(LocalDateTime startTime, LocalDateTime endTime, Printer printer, String userEmail) {

        User user = userService.findByEmail(userEmail);
        Location location = printer.getLocation();

        return reservationRepo.save(new Reservation(startTime, endTime, printer, user, location));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    @Override
    public List<Reservation> findByPrinterId(Long id) {
        List<Reservation> reservations = new ArrayList<>();

        for(Reservation i : reservationRepo.findAll()) {
            if(i.getPrinter().getId().equals(id)) {
                reservations.add(i);
            }
        }
        return reservations;
    }

    @Override
    public List<Reservation> findByUserId(Long id) {
        List<Reservation> reservations = new ArrayList<>();

        for(Reservation i : reservationRepo.findAll()) {
            if(i.getUser().getId().equals(id)) {
                reservations.add(i);
            }
        }
        return reservations;
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id " + id));
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepo.deleteById(id);
    }

    @Override
    public List<Printer> getAvailablePrinters(String startDateTime, String endDateTime) {

        LocalDateTime startTime = LocalDateTime.parse(startDateTime, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endTime = LocalDateTime.parse(endDateTime, DateTimeFormatter.ISO_DATE_TIME);

        List<Printer> availablePrinters = new ArrayList<>();

        outerLoop:
        for (Printer p : printerRepo.findAll()) {
            if (p.isConflicting(startTime, endTime)) {
                continue;
            }
            for (Reservation r : findByPrinterId(p.getId())) {
                if (r.isConflicting(startTime, endTime)) {
                    continue outerLoop;
                }
            }
            availablePrinters.add(p);
        }

        return availablePrinters;
    }

    @Override
    public boolean isWithinLimits(LocalDateTime start, LocalDateTime end, User user) {
        Long duration = Duration.between(start, end).toHours();
        return user.getMsgLimit() - user.getHoursReserved() - duration >= 0;
    }

}
