package co.edu.udea.fabrica_escuela.component.autenticacion.application;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.Role;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.AuthenticationService;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.AuthenticationServiceImpl;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.gateway.UserRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.RoleData;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.UserData;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.mapper.RoleMapper;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.mapper.UserMapper;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.repository.RoleRepository;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import co.edu.udea.fabrica_escuela.config.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AutenticacionConfig {

    @Value("${security.auth.jwt.secret}")
    private String jwtSecret;

    @Value("${security.auth.jwt.expiration}")
    private int jwtExpirationSec;

    @Bean
    public JwtProvider jwtProvider() {
        return JwtProvider.builder()
                .expiration(this.jwtExpirationSec)
                .secret(this.jwtSecret)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public EntityModelMapper<User, UserData> userMapper(RoleRepository roleRepository) {
        return UserMapper.builder()
                .roleRepository(roleRepository)
                .build();
    }

    @Bean
    public EntityModelMapper<Role, RoleData> roleMapper() {
        return new RoleMapper();
    }

    @Bean
    public AuthenticationUtils authUtils(AuthenticationManagerBuilder authManagerBuilder, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        return AuthenticationUtils.builder()
                .authManagerBuilder(authManagerBuilder)
                .jwtProvider(jwtProvider)
                .passwordEncoder(passwordEncoder)
                .build();
    }

    @Bean
    public AuthenticationService authenticationService(
            UserRepositoryGateway userRepositoryGateway,
            AuthenticationUtils authUtils
    ) {
        return AuthenticationServiceImpl.builder()
                .userRepositoryGateway(userRepositoryGateway)
                .authUtils(authUtils)
                .build();
    }

}
