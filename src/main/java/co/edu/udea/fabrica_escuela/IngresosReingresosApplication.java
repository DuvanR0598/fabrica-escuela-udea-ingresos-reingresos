package co.edu.udea.fabrica_escuela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IngresosReingresosApplication {

    public static void main(String[] args) {
        SpringApplication.run(IngresosReingresosApplication.class, args);
    }

}
