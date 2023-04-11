package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.gateway;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.gateway.UserRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.UserData;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.repository.UserRepository;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.adapter.DatabaseAdapter;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryGatewayImpl
        extends DatabaseAdapter<User, UserData, Long, UserRepository>
        implements UserRepositoryGateway {

    @Autowired
    public UserRepositoryGatewayImpl(UserRepository repository, EntityModelMapper<User, UserData> entityModelMapper) {
        super(repository, entityModelMapper);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserData> model = this.repository.findByEmail(email);
        return model.map(this.entityModelMapper::toEntity);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserData> model = this.repository.findByUsername(username);
        return model.map(this.entityModelMapper::toEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.repository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return  this.repository.existsByUsername(username);
    }

    @Override
    public void updatePassword(User userToUpdate) {
        Optional<UserData> userDataOptional = this.repository.findByEmail(userToUpdate.getEmail());
        userDataOptional.ifPresent(userData -> userData.setPassword(userToUpdate.getPassword()));
    }

    @Override
    public User save(User model) {
        UserData userSaved = this.repository.save(this.entityModelMapper.toModel(model));
        return this.entityModelMapper.toEntity(userSaved);
    }

}
