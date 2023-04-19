package co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.adapter;

import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class DatabaseAdapter<E,M, I, R extends JpaRepository<M, I> & QueryByExampleExecutor<M>> {

    private final R repository;
    private final EntityModelMapper<E, M> entityModelMapper;

    public M saveModel(M model) {
        return this.repository.save(model);
    }

    public void saveEntity(E entity) {
        var model = this.toModel(entity);
        var x = this.repository.save(model);
        System.out.println(x);
    }

    public Optional<M> findById(I id) {
        return repository.findById(id);
    }

    public List<M> findAllByCriteria(Example<M> example) {
        return this.repository.findAll(example);
    }

    public Optional<M> findOneByCriteria(Example<M> example) {
        return this.repository.findOne(example);
    }

    public boolean existsByCriteria(Example<M> example) {
        return this.repository.exists(example);
    }

    protected M toModel(E entity) {
        return this.entityModelMapper.toModel(entity);
    }

    protected E toEntity(M model) {
        return this.entityModelMapper.toEntity(model);
    }
    
}
