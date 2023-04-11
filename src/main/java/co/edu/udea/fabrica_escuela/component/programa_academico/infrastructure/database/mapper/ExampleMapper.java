package co.edu.udea.fabrica_escuela.component.programa_academico.infrastructure.database.mapper;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.ExampleEntity;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.ExampleEntityData;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExampleMapper implements EntityModelMapper<ExampleEntity, ExampleEntityData> {

    @Override
    public ExampleEntityData toModel(ExampleEntity entity) {
        return null;
    }

    @Override
    public List<ExampleEntityData> toModel(List<ExampleEntity> entities) {
        return null;
    }

    @Override
    public ExampleEntity toEntity(ExampleEntityData model) {
        return null;
    }

    @Override
    public List<ExampleEntity> toEntity(List<ExampleEntityData> models) {
        return null;
    }
}
