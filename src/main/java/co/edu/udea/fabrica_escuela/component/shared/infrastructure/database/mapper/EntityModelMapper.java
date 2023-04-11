package co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper;

import java.util.List;

public interface EntityModelMapper<E, M> {

    M toModel(E entity);

    List<M> toModel(List<E> entities);

    E toEntity(M model);

    List<E> toEntity(List<M> models);


}
