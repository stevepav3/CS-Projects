package com.printerscheduler.server;

import com.printerscheduler.server.model.*;
import com.printerscheduler.server.repo.LocationRepo;
import com.printerscheduler.server.repo.OperatingHoursRepo;
import com.printerscheduler.server.repo.PrinterRepo;
import com.printerscheduler.server.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner insertInitialData(PrinterRepo printerRepository,
											   OperatingHoursRepo operatingHoursRepository,
											   LocationRepo locationRepository,
											   UserRepo userRepository) {
		return args -> {

			OperatingHours defaultOpHours = new OperatingHours();
			Location loc1 = new Location("Engineering Lab");
			Location loc2 = new Location("Robotics Lab");

			// Add default operating hours if none present in table
			if(operatingHoursRepository.count() == 0) {
				defaultOpHours = operatingHoursRepository.save(defaultOpHours);
			}
			else {
				defaultOpHours = operatingHoursRepository.findAll().iterator().next();
			}

			// Add default locations if none present in table
			if (locationRepository.count() == 0) {
				loc1 = locationRepository.save(loc1);
				loc2 = locationRepository.save(loc2);
			} else {
				List<Location> locations = new ArrayList<>();
				locationRepository.findAll().forEach(locations::add);

				loc1 = locations.stream().filter(l -> l.getLocationName().equals("Engineering Lab")).findFirst().orElse(loc1);
				loc2 = locations.stream().filter(l -> l.getLocationName().equals("Robotics Lab")).findFirst().orElse(loc2);
			}


			// Add default printers if none present in table
			if(printerRepository.count() == 0) {
				Printer printer1 = new Printer(1L, "Printer 1", "Model 1", "imageUrl1", loc1, false, defaultOpHours);
				Printer printer2 = new Printer(2L, "Printer 2", "Model 2", "imageUrl2", loc2, false, defaultOpHours);
				Printer printer3 = new Printer(3L, "Printer 3", "Model 3", "imageUrl3", loc2, false, defaultOpHours);

				printerRepository.saveAll(Arrays.asList(printer1, printer2, printer3));
			}


			// Add a default admin and user if none present in table
			if(userRepository.count() == 0) {
				User admin = new User(1L, "Admin", "1", "admin1@gmail.com", passwordEncoder.encode("password"), 0, true, Role.ADMIN);
				User user = new User(2L, "User", "1", "user1@gmail.com", passwordEncoder.encode("password"), 0, true, Role.USER);

				userRepository.saveAll(Arrays.asList(admin, user));
			}
		};
	}


	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));

		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));

		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));

		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}