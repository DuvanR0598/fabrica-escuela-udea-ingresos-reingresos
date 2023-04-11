package co.edu.udea.fabrica_escuela.component.autenticacion.domain.service;

import co.edu.udea.fabrica_escuela.component.autenticacion.application.AutenticacionUtils;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.EnumRole;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.PasswordRecoveryPerformCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.PreRegisterRemoveCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserLoginCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserRegisterCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.query.ExampleQuery;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.gateway.UserRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.response.JwtResponseDto;
import co.edu.udea.fabrica_escuela.component.shared.domain.core.exception.MissingPreRegisterException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Builder(toBuilder = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PreRegistrationService preRegistrationService;

    private final UserRepositoryGateway userRepositoryGateway;

    private final AutenticacionUtils authUtils;

    @Override
    public boolean existsByEmail(ExampleQuery emailExistenceQuery) {
        return this.userRepositoryGateway.existsByEmail(emailExistenceQuery.getEmail());
    }

    /**
     * @param userRegisterCommand Command with necessary information to register the user
     * @throws MissingPreRegisterException Exception thrown when default role is missing
     */
    @Override
    public void registerUser(final UserRegisterCommand userRegisterCommand) {
        User userToRegister = UserRegisterCommand.toEntity(userRegisterCommand);
        // Encrypt the password
        userToRegister.setPassword(this.authUtils.getPasswordEncoder()
                .encode(userRegisterCommand.getPassword()));

        // Add authorities
        userToRegister.setGrantedAuthorities(userRegisterCommand.getRoles()
                .stream()
                .map(enumRole -> new SimpleGrantedAuthority(enumRole.name()))
                .collect(Collectors.toSet())
        );

        this.userRepositoryGateway.save(userToRegister);
        PreRegisterRemoveCommand preRegisterRemoveCommand = UserRegisterCommand.toPreRegisterRemoveCommand(userRegisterCommand);
        this.preRegistrationService.removePreRegistration(preRegisterRemoveCommand);
    }

    /**
     * @param userLoginCommand Command with necessary information to log in the user
     * @return The JWT generated for the passive session
     */
    @Override
    public JwtResponseDto loginUser(UserLoginCommand userLoginCommand) {
        Authentication authentication =
                this.authUtils.getAuthManagerBuilder()
                        .getObject().authenticate(new UsernamePasswordAuthenticationToken(
                                userLoginCommand.getUsername(),
                                userLoginCommand.getPassword())
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.authUtils.getJwtProvider().generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return JwtResponseDto.builder()
                .token(jwt)
                .ok(Boolean.TRUE)
                .username(userDetails.getUsername())
                .authorities(userDetails.getAuthorities())
                .build();
    }

    /**
     * @param token Current token to be validated and refreshed
     * @return The new token after validations
     */
    @Override
    public String refreshToken(String token) {
        return this.authUtils.getJwtProvider()
                .refreshToken(token);
    }

    @Override
    public String getUsernameFromToken(String newToken) {
        return this.authUtils.getJwtProvider()
                .getUsernameFromToken(newToken);
    }

    @Override
    public Collection<? extends GrantedAuthority> getRolesFromToken(String newToken) {
        return this.authUtils.getJwtProvider()
                .getRolesFromToken(newToken);
    }

    @Override
    public Optional<User> getByUsername(String username) throws UsernameNotFoundException {
        return this.userRepositoryGateway.findByUsername(username);
    }

    @Override
    public Set<String> filterExistingUsers(Set<ExampleQuery> emailExistenceQueries) {
        return emailExistenceQueries.stream()
                .filter(this::existsByEmail)
                .map(ExampleQuery::getEmail)
                .collect(Collectors.toSet());
    }

    @Override
    public void updatePassword(PasswordRecoveryPerformCommand passwordRecoveryPerformCommand) {
        // Up to this point, everything is valid
        User userToUpdate = PasswordRecoveryPerformCommand.toEntity(passwordRecoveryPerformCommand);
        // Encrypt the password
        userToUpdate.setPassword(this.authUtils.getPasswordEncoder()
                .encode(userToUpdate.getPassword()));
        this.userRepositoryGateway.updatePassword(userToUpdate);

    }

    @Override
    public void registerAdmin(UserRegisterCommand adminRegisterCommand) {
        User adminToRegister = UserRegisterCommand.toEntity(adminRegisterCommand);
        // Encrypt the password
        adminToRegister.setPassword(this.authUtils.getPasswordEncoder()
                .encode(adminRegisterCommand.getPassword()));
        // Add admin authority
        adminToRegister.setGrantedAuthorities(
                Set.of(new SimpleGrantedAuthority(EnumRole.ROLE_SUPER_ADMIN.name()))
        );
        this.userRepositoryGateway.save(adminToRegister);
    }
}
