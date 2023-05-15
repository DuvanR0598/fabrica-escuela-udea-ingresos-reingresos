package co.edu.udea.fabrica_escuela.component.admisiones.application;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.Admision;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.services.AdmisionesService;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.services.AdmisionesServiceImpl;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.services.gateway.AdmisionesRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.AdmisionData;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.mapper.AdmisionesMapper;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdmisionesConfig {

    @Bean
    public EntityModelMapper<Admision, AdmisionData> admisionMapper() {
        return AdmisionesMapper.builder().build();
    }

    @Bean
    public AdmisionesService admisionesService(
            AdmisionesRepositoryGateway admisionesRepositoryGateway
    ) {
        return AdmisionesServiceImpl.builder()
                .admisionesRepositoryGateway(admisionesRepositoryGateway)
                .build();
    }

}
