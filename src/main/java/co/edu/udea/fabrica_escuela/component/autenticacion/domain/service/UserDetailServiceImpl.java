package co.edu.udea.fabrica_escuela.component.autenticacion.domain.service;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import co.edu.udea.fabrica_escuela.component.shared.domain.services.RestServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final AuthenticationService authenticationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RestServiceResponse<User> response = this.authenticationService.getByUsername(username);
        if(response.getStatusCode().isError()) {
            throw new UsernameNotFoundException("Username " + username + " was not found.");
        }
        return response.getResponse();
    }

}
