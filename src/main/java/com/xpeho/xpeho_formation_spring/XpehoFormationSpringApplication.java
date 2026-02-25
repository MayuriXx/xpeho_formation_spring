package com.xpeho.xpeho_formation_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the XPEHO Formation Spring Boot application.
 * <p>
 * This application is a RESTful API for managing movies. It demonstrates
 * Clean Architecture principles with clear separation of concerns across
 * presentation, domain, and data layers.
 * <p>
 * The application uses:
 * - Spring Boot 4.0.2
 * - Spring Data JDBC for persistence
 * - H2/PostgreSQL for database (configurable)
 * - Swagger/OpenAPI for API documentation
 * <p>
 * Main API endpoint: GET/POST/PUT/DELETE /movies
 *
 * @author XPEHO
 */
@SpringBootApplication
public class XpehoFormationSpringApplication {

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args command-line arguments passed to the application
     */
    static void main(String[] args) {
        SpringApplication.run(XpehoFormationSpringApplication.class, args);
    }

}
