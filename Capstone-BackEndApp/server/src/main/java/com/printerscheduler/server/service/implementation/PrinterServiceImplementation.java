package com.printerscheduler.server.service.implementation;

import com.printerscheduler.server.model.Printer;
import com.printerscheduler.server.model.Reservation;
import com.printerscheduler.server.repo.PrinterRepo;
import com.printerscheduler.server.service.PrinterService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrinterServiceImplementation implements PrinterService {

    private final PrinterRepo printerRepo;

    @Autowired
    public PrinterServiceImplementation(PrinterRepo printerRepo) {
        this.printerRepo = printerRepo;
    }

    @Override
    public Printer create(Printer printer) {
        return printerRepo.save(printer);
    }

    @Override
    public List<Printer> findAll() {
        return printerRepo.findAll();
    }

    @Override
    public List<Printer> findAllAvailable() {
        List<Printer> allPrinters = findAll();
        List<Printer> availablePrinters = new ArrayList<>();

        for(Printer i : allPrinters) {
            if(!i.isInUse()) {
                availablePrinters.add(i);
            }
        }

        return availablePrinters;
    }

    @Override
    public Printer findById(Long id) {
        return printerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Printer not found with id " + id));
    }

    @Override
    public List<Printer> findByLocation(String location) {
        List<Printer> allPrinters = findAll();
        List<Printer> availablePrinters = new ArrayList<>();

        for(Printer i : allPrinters) {
            if(i.getLocation().equals(location)) {
                availablePrinters.add(i);
            }
        }

        return availablePrinters;
    }

    @Override
    public List<Printer> findByModel(String model) {
        List<Printer> allPrinters = findAll();
        List<Printer> availablePrinters = new ArrayList<>();

        for(Printer i : allPrinters) {
            if(i.getModel().equals(model)) {
                availablePrinters.add(i);
            }
        }

        return availablePrinters;
    }

    @Override
    public Printer save(Printer printer) {
        return printerRepo.save(printer);
    }

    @Override
    public void deleteById(Long id) {
        printerRepo.deleteById(id);
    }

    @Override
    public void updatePrinterStatus(Long id, boolean inUse) {
        Printer printer = printerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Printer not found with id " + id));

        printer.setInUse(inUse);
    }

}
