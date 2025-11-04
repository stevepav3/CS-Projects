package com.printerscheduler.server.repo;

import com.printerscheduler.server.model.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrinterRepo extends JpaRepository<Printer, Long> {
}
