package co.edu.udea.fabrica_escuela.component.programa_academico.infrastructure.gateway;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.ExampleEntity;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.service.gateway.ExampleRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.ExampleEntityData;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.repository.ExampleRepository;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.adapter.DatabaseAdapter;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleRepositoryGatewayImpl
        extends DatabaseAdapter<ExampleEntity, ExampleEntityData, Long, ExampleRepository>
        implements ExampleRepositoryGateway {

    public ExampleRepositoryGatewayImpl(ExampleRepository repository, EntityModelMapper<ExampleEntity, ExampleEntityData> entityModelMapper) {
        super(repository, entityModelMapper);
    }

}
