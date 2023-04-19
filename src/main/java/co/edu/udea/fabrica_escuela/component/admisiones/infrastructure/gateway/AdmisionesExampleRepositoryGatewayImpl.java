package co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.gateway;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.AdmisionesExampleEntity;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.service.gateway.AdmisionesExampleRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.AdmisionesExampleEntityData;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.repository.AdmisionesExampleRepository;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.adapter.DatabaseAdapter;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AdmisionesExampleRepositoryGatewayImpl
        extends DatabaseAdapter<AdmisionesExampleEntity, AdmisionesExampleEntityData, Long, AdmisionesExampleRepository>
        implements AdmisionesExampleRepositoryGateway {

    public AdmisionesExampleRepositoryGatewayImpl(AdmisionesExampleRepository repository, EntityModelMapper<AdmisionesExampleEntity, AdmisionesExampleEntityData> entityModelMapper) {
        super(repository, entityModelMapper);
    }

}
