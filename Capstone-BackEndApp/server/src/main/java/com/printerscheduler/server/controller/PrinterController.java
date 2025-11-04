package com.printerscheduler.server.controller;

import com.printerscheduler.server.model.Printer;
import com.printerscheduler.server.service.PrinterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/printer")
public class PrinterController {

    private final PrinterService printerService;

    public PrinterController(PrinterService printerService) {
        this.printerService = printerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Printer> createPrinter(@RequestBody Printer printer) {
        return new ResponseEntity<>(printerService.create(printer), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Printer>> getAllPrinters() {
        return new ResponseEntity<>(printerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/all/available")
    public ResponseEntity<List<Printer>> getAllAvailablePrinters() {
        return new ResponseEntity<>(printerService.findAllAvailable(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Printer> getPrinterById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(printerService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/find/location/{location}")
    public ResponseEntity<List<Printer>> getPrintersByLocation(@PathVariable("location") String location) {
        return new ResponseEntity<>(printerService.findByLocation(location), HttpStatus.OK);
    }

    @GetMapping("/find/model/{model}")
    public ResponseEntity<List<Printer>> getPrintersByModel(@PathVariable("model") String model) {
        return new ResponseEntity<>(printerService.findByModel(model), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Printer> updatePrinter(@PathVariable("id") Long id, @RequestBody Printer printer) {
        printer.setId(id);
        return new ResponseEntity<>(printer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePrinter(@PathVariable("id") Long id) {
        printerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
