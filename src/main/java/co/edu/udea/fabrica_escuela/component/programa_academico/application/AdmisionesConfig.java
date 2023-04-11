package co.edu.udea.fabrica_escuela.component.programa_academico.application;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.service.ExampleService;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.service.ExampleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdmisionesConfig {

    @Bean
    public ExampleService exampleService(
    ) {
        return ExampleServiceImpl.builder()
                .build();
    }

}
