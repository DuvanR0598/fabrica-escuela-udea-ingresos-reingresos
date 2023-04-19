package co.edu.udea.fabrica_escuela.config.security.jwt;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import io.jsonwebtoken.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Log
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class JwtProvider {

    private final String secret;
    private final int expiration;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(user.getUsername())
                // --- We can add claims to the payload ---
                //.addClaims(add some claims)
                .claim("roles", user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                ).claim("email", user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + this.expiration * 1000L))
                .signWith(SignatureAlgorithm.HS256, this.secret).compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e1) {
            log.log(Level.SEVERE, "Token is invalid");
        } catch (UnsupportedJwtException e2) {
            log.log(Level.SEVERE, "Token not supported");
        } catch (ExpiredJwtException e3) {
            log.log(Level.SEVERE, "Token has expired");
        } catch (IllegalArgumentException e4) {
            log.log(Level.SEVERE, "Token is empty");
        } catch (SignatureException e5) {
            log.log(Level.SEVERE, "Signature is invalid");
        }
        return false;
    }

    public String refreshToken(String token) {
        token = token.replace("Bearer ", "").trim();
        if (!this.isTokenValid(token)) {
            return null;
        }
        String username = this.getUsernameFromToken(token);
        String email = this.getClaimFromToken(token, String.class);
        List<GrantedAuthority> authorities = this.getRolesFromToken(token);
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", authorities
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                )
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + this.expiration * 1000L))
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    private <T> T getClaimFromToken(String token, Class<T> valueType) {
        return Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody()
                .get("email", valueType);
    }

    @SuppressWarnings("unchecked")
    public List<GrantedAuthority> getRolesFromToken(String token) {
        return (List<GrantedAuthority>) Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody()
                .get("roles", List.class).stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());
    }

}
