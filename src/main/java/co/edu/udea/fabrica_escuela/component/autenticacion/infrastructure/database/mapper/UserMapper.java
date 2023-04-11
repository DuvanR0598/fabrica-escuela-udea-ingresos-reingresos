package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.mapper;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.EnumRole;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.UserData;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.repository.RoleRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Builder(toBuilder = true)
public class UserMapper implements EntityModelMapper<User, UserData> {

    private final RoleRepository roleRepository;

    @Override
    public UserData toModel(User entity) {
        return UserData.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .firstName(entity.getFirstName())
                .middleName(entity.getLastName())
                .roles(entity.getAuthorities().stream()
                        .map(grantedAuthority -> EnumRole.valueOf(grantedAuthority.getAuthority()))
                        .map(this.roleRepository::findByRole)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List<UserData> toModel(List<User> entities) {
        return entities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public User toEntity(UserData model) {
        return User.builder()
                .id(model.getId())
                .username(model.getUsername())
                .email(model.getEmail())
                .password(model.getPassword())
                .firstName(model.getFirstName())
                .lastName(model.getMiddleName())
                .grantedAuthorities(model.getRoles().stream()
                        .map(item -> new SimpleGrantedAuthority(item.getRole().name()))
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List<User> toEntity(List<UserData> models) {
        return models.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
