package co.edu.udea.fabrica_escuela.component.autenticacion.domain.service;

import co.edu.udea.fabrica_escuela.component.autenticacion.application.AuthenticationUtils;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.Role;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserLoginCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserRegisterCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.query.RefreshTokenQuery;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.gateway.UserRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.response.JwtResponseDto;
import co.edu.udea.fabrica_escuela.component.shared.domain.services.RestServiceResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Builder(toBuilder = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepositoryGateway userRepositoryGateway;
    private final AuthenticationUtils authUtils;

    @Override
    public RestServiceResponse<Void> registerUser(final UserRegisterCommand userRegisterCommand) {
        boolean emailAlreadyUsed = this.userRepositoryGateway.existsByEmail(userRegisterCommand.getEmail());
        if (emailAlreadyUsed)
            return RestServiceResponse.of(HttpStatus.CONFLICT);
        User userToRegister = UserRegisterCommand.toEntity(userRegisterCommand);

        // Encrypt the password
        userToRegister.setPassword(this.authUtils.getPasswordEncoder()
                .encode(userRegisterCommand.getPassword()));
        userToRegister.setActive(true);
        // Add authorities
        userToRegister.setGrantedAuthorities(Set.of(new SimpleGrantedAuthority(Role.EnumRole.ROLE_STUDENT.name())));

        this.userRepositoryGateway.saveUser(userToRegister);
        return RestServiceResponse.of(HttpStatus.CREATED);
    }

    @Override
    public RestServiceResponse<JwtResponseDto> loginUser(UserLoginCommand userLoginCommand) {
        Authentication authentication =
                this.authUtils.getAuthManagerBuilder()
                        .getObject()
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                userLoginCommand.getUsername(),
                                userLoginCommand.getPassword())
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.authUtils.getJwtProvider().generateToken(authentication);
        User userDetails = (User) authentication.getPrincipal();

        JwtResponseDto jwtResponseDto = JwtResponseDto.builder()
                .token(jwt)
                .ok(Boolean.TRUE)
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .build();

        return RestServiceResponse.of(HttpStatus.CREATED, jwtResponseDto);
    }

    @Override
    public RestServiceResponse<JwtResponseDto> refreshToken(RefreshTokenQuery refreshTokenQuery) {
        String token = this.authUtils.getJwtProvider()
                .refreshToken(refreshTokenQuery.getToken());
        JwtResponseDto response = JwtResponseDto.builder()
                .ok(true)
                .token(token)
                .build();
        return RestServiceResponse.of(HttpStatus.OK, response);
    }
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
    public RestServiceResponse<User> getByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepositoryGateway.findEntityByUsername(username);
        return userOptional.map(user -> RestServiceResponse.of(HttpStatus.OK, user))
                .orElseGet(() -> RestServiceResponse.of(HttpStatus.NOT_FOUND));
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
