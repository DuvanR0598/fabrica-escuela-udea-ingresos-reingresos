package co.edu.udea.fabrica_escuela.component.programacademico.infrastructure.gateway;

import co.edu.udea.fabrica_escuela.component.programacademico.domain.ProgramaAcademicoExampleEntity;
import co.edu.udea.fabrica_escuela.component.programacademico.domain.service.gateway.ProgramaAcademicoExampleRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.programacademico.infrastructure.database.ProgramaAcademicoExampleEntityData;
import co.edu.udea.fabrica_escuela.component.programacademico.infrastructure.database.repository.ProgramaAcademicoExampleRepository;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.adapter.DatabaseAdapter;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProgramaAcademicoExampleRepositoryGatewayImpl
        extends DatabaseAdapter<ProgramaAcademicoExampleEntity, ProgramaAcademicoExampleEntityData, Long, ProgramaAcademicoExampleRepository>
        implements ProgramaAcademicoExampleRepositoryGateway {

    public ProgramaAcademicoExampleRepositoryGatewayImpl(ProgramaAcademicoExampleRepository repository, EntityModelMapper<ProgramaAcademicoExampleEntity, ProgramaAcademicoExampleEntityData> entityModelMapper) {
        super(repository, entityModelMapper);
    }

}
