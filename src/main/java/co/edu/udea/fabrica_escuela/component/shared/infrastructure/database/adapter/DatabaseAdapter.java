package co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.adapter;

import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class DatabaseAdapter<E,M, I, R extends JpaRepository<M, I> & QueryByExampleExecutor<M>> {

    protected final R repository;
    protected final EntityModelMapper<E, M> entityModelMapper;

    public M save(M model) {
        return this.saveModel(model);
    }

    public M findById(I id) {
        Optional<M> modelOptional = repository.findById(id);
        return modelOptional.orElse(null);
    }

    public List<M> findByExample(E entity) {
        return this.repository.findAll();
    }

    protected M toModel(E entity) {
        return this.entityModelMapper.toModel(entity);
    }

    protected E toEntity(M model) {
        return this.entityModelMapper.toEntity(model);
    }

    protected M saveModel(M model) {
        return repository.save(model);
    }

}
