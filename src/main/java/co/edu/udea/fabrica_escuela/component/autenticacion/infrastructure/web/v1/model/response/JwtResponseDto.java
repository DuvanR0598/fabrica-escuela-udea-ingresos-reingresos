package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.response;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class JwtResponseDto {

    private static final String TOKEN_PREFIX="Bearer";

    private boolean ok;
    private String token;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

}
