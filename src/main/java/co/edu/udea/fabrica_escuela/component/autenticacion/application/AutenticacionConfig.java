package co.edu.udea.fabrica_escuela.component.autenticacion.application;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.*;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.gateway.PreRegisterRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.PasswordRecoveryData;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.PreRegisterData;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.RoleData;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.UserData;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.mapper.PasswordRecoveryMapper;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.mapper.PreRegisterMapper;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.mapper.RoleMapper;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.mapper.UserMapper;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.repository.RoleRepository;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.database.mapper.EntityModelMapper;
import co.edu.udea.fabrica_escuela.config.messaging.GmailMessageSender;
import co.gov.cancer.co.edu.udea.fabrica_escuela.component.personas.domain.service.*;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.EnumRole;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.PasswordRecovery;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.PreRegister;
import co.gov.cancer.spc.component.personas.domain.service.*;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.gateway.PasswordRecoveryRepositoryGateway;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.service.gateway.UserRepositoryGateway;
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
    public EntityModelMapper<EnumRole, RoleData> roleMapper() {
        return new RoleMapper();
    }

    @Bean
    public EntityModelMapper<PreRegister, PreRegisterData> preRegisterMapper(EntityModelMapper<EnumRole, RoleData> roleMapper) {
        return PreRegisterMapper.builder()
                .roleMapper(((RoleMapper) roleMapper))
                .build();
    }

    @Bean
    public EntityModelMapper<PasswordRecovery, PasswordRecoveryData> passwordRecoveryMapper() {
        return new PasswordRecoveryMapper();
    }

    @Bean
    public AutenticacionUtils authUtils(AuthenticationManagerBuilder authManagerBuilder, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        return AutenticacionUtils.builder()
                .authManagerBuilder(authManagerBuilder)
                .jwtProvider(jwtProvider)
                .passwordEncoder(passwordEncoder)
                .build();
    }

    @Bean
    public PreRegistrationService preRegistrationService(PreRegisterRepositoryGateway preRegisterRepositoryGateway, GmailMessageSender gmailMessageSender) {
        return PreRegistrationServiceImpl.builder()
                .gmailMessageSender(gmailMessageSender)
                .preRegisterRepositoryGateway(preRegisterRepositoryGateway)
                .build();
    }

    @Bean
    public PasswordRecoveryService passwordRecoveryService(PasswordRecoveryRepositoryGateway passwordRecoveryRepositoryGateway, GmailMessageSender gmailMessageSender, AuthenticationService authenticationService) {
        return PasswordRecoveryServiceImpl.builder()
                .authenticationService(authenticationService)
                .passwordRecoveryRepositoryGateway(passwordRecoveryRepositoryGateway)
                .gmailMessageSender(gmailMessageSender)
                .build();
    }

    @Bean
    public AuthenticationService authenticationService(
            PreRegistrationService preRegistrationService,
            UserRepositoryGateway userRepositoryGateway,
            AutenticacionUtils authUtils
    ) {
        return AuthenticationServiceImpl.builder()
                .preRegistrationService(preRegistrationService)
                .userRepositoryGateway(userRepositoryGateway)
                .authUtils(authUtils)
                .build();
    }

}
