package co.edu.udea.fabrica_escuela.component.autenticacion.domain.service;

import co.edu.udea.fabrica_escuela.component.autenticacion.application.AutenticacionUtils;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserRegisterCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.query.ExampleQuery;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.gateway.UserRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.shared.domain.services.RestServiceResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Builder(toBuilder = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepositoryGateway userRepositoryGateway;
    private final AutenticacionUtils authUtils;

    private boolean existsByEmail(ExampleQuery emailExistenceQuery) {
        return this.userRepositoryGateway.existsByEmail(emailExistenceQuery.getEmail());
    }

    @Override
    public RestServiceResponse<Void> registerUser(final UserRegisterCommand userRegisterCommand) {
        boolean isAlreadyRegistered = this.userRepositoryGateway.existsByEmail(userRegisterCommand.getEmail());
        if (isAlreadyRegistered) {
            return RestServiceResponse.of(HttpStatus.CONFLICT);
        }
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
        return RestServiceResponse.of(HttpStatus.CREATED);
    }

    //    /**
//     * @param usuarioLoginCommand Command with necessary information to log in the user
//     * @return The JWT generated for the passive session
//     */
//    @Override
//    public RestServiceResponse<JwtResponseDto> loginUser(UsuarioLoginCommand usuarioLoginCommand) {
//        Authentication authentication =
//                this.authUtils.getAuthManagerBuilder()
//                        .getObject().authenticate(new UsernamePasswordAuthenticationToken(
//                                usuarioLoginCommand.getUsername(),
//                                usuarioLoginCommand.getPassword())
//                        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = this.authUtils.getJwtProvider().generateToken(authentication);
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//        return JwtResponseDto.builder()
//                .token(jwt)
//                .ok(Boolean.TRUE)
//                .username(userDetails.getUsername())
//                .authorities(userDetails.getAuthorities())
//                .build();
//    }
//
//    /**
//     * @param token Current token to be validated and refreshed
//     * @return The new token after validations
//     */
//    @Override
//    public String refreshToken(String token) {
//        return this.authUtils.getJwtProvider()
//                .refreshToken(token);
//    }
//
//    @Override
//    public String getUsernameFromToken(String newToken) {
//        return this.authUtils.getJwtProvider()
//                .getUsernameFromToken(newToken);
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getRolesFromToken(String newToken) {
//        return this.authUtils.getJwtProvider()
//                .getRolesFromToken(newToken);
//    }
//
    @Override
    public RestServiceResponse<Optional<User>> getByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepositoryGateway.findByUsername(username);
        return RestServiceResponse.of(HttpStatus.OK, user);
    }
//
//    @Override
//    public Set<String> filterExistingUsers(Set<ExampleQuery> emailExistenceQueries) {
//        return emailExistenceQueries.stream()
//                .filter(this::existsByEmail)
//                .map(ExampleQuery::getEmail)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public void updatePassword(PasswordRecoveryPerformCommand passwordRecoveryPerformCommand) {
//        // Up to this point, everything is valid
//        User userToUpdate = PasswordRecoveryPerformCommand.toEntity(passwordRecoveryPerformCommand);
//        // Encrypt the password
//        userToUpdate.setPassword(this.authUtils.getPasswordEncoder()
//                .encode(userToUpdate.getPassword()));
//        this.userRepositoryGateway.updatePassword(userToUpdate);
//
//    }
//
//    @Override
//    public void registerAdmin(UserRegisterCommand adminRegisterCommand) {
//        User adminToRegister = UserRegisterCommand.toEntity(adminRegisterCommand);
//        // Encrypt the password
//        adminToRegister.setPassword(this.authUtils.getPasswordEncoder()
//                .encode(adminRegisterCommand.getPassword()));
//        // Add admin authority
//        adminToRegister.setGrantedAuthorities(
//                Set.of(new SimpleGrantedAuthority(EnumRole.ROLE_SUPER_ADMIN.name()))
//        );
//        this.userRepositoryGateway.save(adminToRegister);
//    }

}
