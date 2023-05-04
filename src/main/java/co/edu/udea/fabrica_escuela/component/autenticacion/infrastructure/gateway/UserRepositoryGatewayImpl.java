package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.gateway;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.gateway.UserRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.UserData;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.repository.UserRepository;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.adapter.DatabaseAdapter;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryGatewayImpl
        extends DatabaseAdapter<User, UserData, Long, UserRepository>
        implements UserRepositoryGateway {

    @Override
    public void saveUser(User entity) {
        this.saveEntity(entity);
    }

    @Autowired
    public UserRepositoryGatewayImpl(UserRepository repository, EntityModelMapper<User, UserData> entityModelMapper) {
        super(repository, entityModelMapper);
    }

    @Override
    public Optional<UserData> findByEmail(String email) {
        UserData example = UserData.builder()
                .email(email)
                .build();
        return this.findOneByCriteria(Example.of(example));
    }

    @Override
    public Optional<UserData> findByUsername(String username) {
        UserData example = UserData.builder()
                .username(username)
                .build();
        return this.repository.findByUsername(username);
    }

    @Override
    public Optional<User> findEntityByUsername(String username) {
        Optional<UserData> userDataOptional = this.findByUsername(username);
        return userDataOptional.map(this::toEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        UserData example = UserData.builder()
                .email(email)
                .build();
        return this.existsByCriteria(Example.of(example));
    }

    @Override
    public boolean existsByUsername(String username) {
        UserData example = UserData.builder()
                .username(username)
                .build();
        return this.existsByCriteria(Example.of(example));
    }

    @Override
    public void updatePassword(User userToUpdate) {
        UserData example = UserData.builder()
                .username(userToUpdate.getUsername())
                .build();
        Optional<UserData> userDataOptional = this.findOneByCriteria(Example.of(example));
        userDataOptional.ifPresent(userData -> userData.setPassword(userToUpdate.getPassword()));
    }

}
