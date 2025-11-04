package com.printerscheduler.server.service;

import com.printerscheduler.server.model.Printer;
import com.printerscheduler.server.model.Reservation;

import java.util.List;

public interface PrinterService {
    Printer create(Printer printer);
    List<Printer> findAll();
    List<Printer> findAllAvailable();
    Printer findById(Long id);
    List<Printer> findByLocation(String location);
    List<Printer> findByModel(String model);
    Printer save(Printer printer);
    void deleteById(Long id);
    void updatePrinterStatus(Long id, boolean inUse);


}
