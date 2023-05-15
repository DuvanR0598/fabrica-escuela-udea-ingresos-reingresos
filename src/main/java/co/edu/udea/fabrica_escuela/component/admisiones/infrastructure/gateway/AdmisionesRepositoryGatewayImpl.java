package co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.gateway;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.Admision;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.services.gateway.AdmisionesRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.AdmisionData;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.repository.AdmisionRepository;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.adapter.DatabaseAdapter;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AdmisionesRepositoryGatewayImpl
        extends DatabaseAdapter<Admision, AdmisionData, Long, AdmisionRepository>
        implements AdmisionesRepositoryGateway {

    public AdmisionesRepositoryGatewayImpl(AdmisionRepository repository, EntityModelMapper<Admision, AdmisionData> entityModelMapper) {
        super(repository, entityModelMapper);
    }

    @Override
    public void saveAdmision(Admision entity) {
        this.saveEntity(entity);
    }
}
