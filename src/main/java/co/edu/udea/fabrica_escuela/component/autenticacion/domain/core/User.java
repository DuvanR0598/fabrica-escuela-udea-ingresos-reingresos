package co.edu.udea.fabrica_escuela.component.autenticacion.domain.core;

import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database.UserData;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder(toBuilder = true)
public class User implements UserDetails {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
    private Set<? extends GrantedAuthority> grantedAuthorities;
    private boolean active;

    public static User build(UserData userData) {
        Set<GrantedAuthority> authorities = userData.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getValue().name()))
                .collect(Collectors.toSet());

        return User.builder()
                .username(userData.getUsername())
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .email(userData.getEmail())
                .phoneNumber(userData.getPhoneNumber())
                .address(userData.getAddress())
                .password(userData.getPassword())
                .grantedAuthorities(authorities)
                .active(userData.isActive())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
