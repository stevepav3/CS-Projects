package com.printerscheduler.server.repo;

import com.printerscheduler.server.model.OperatingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatingHoursRepo extends JpaRepository<OperatingHours, Long> {
}
