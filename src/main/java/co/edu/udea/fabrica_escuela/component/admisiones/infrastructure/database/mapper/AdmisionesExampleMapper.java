package co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.mapper;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.AdmisionesExampleEntity;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.AdmisionesExampleEntityData;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdmisionesExampleMapper implements EntityModelMapper<AdmisionesExampleEntity, AdmisionesExampleEntityData> {

    @Override
    public AdmisionesExampleEntityData toModel(AdmisionesExampleEntity entity) {
        return null;
    }

    @Override
    public List<AdmisionesExampleEntityData> toModel(List<AdmisionesExampleEntity> entities) {
        return null;
    }

    @Override
    public AdmisionesExampleEntity toEntity(AdmisionesExampleEntityData model) {
        return null;
    }

    @Override
    public List<AdmisionesExampleEntity> toEntity(List<AdmisionesExampleEntityData> models) {
        return null;
    }
}
