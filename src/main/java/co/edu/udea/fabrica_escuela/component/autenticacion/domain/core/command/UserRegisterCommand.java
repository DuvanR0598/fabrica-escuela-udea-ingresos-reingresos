package co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command;


import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.EnumRole;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserRegisterCommand {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
    private LocalDate registerDate;
    private Set<EnumRole> roles;

    public static User toEntity(UserRegisterCommand userRegisterCommand) {
        return User.builder()
                .username(userRegisterCommand.getEmail().split("@")[0].trim())
                .firstName(userRegisterCommand.getFirstName())
                .lastName(userRegisterCommand.getLastName())
                .email(userRegisterCommand.getEmail())
                .phoneNumber(userRegisterCommand.getPhoneNumber())
                .address(userRegisterCommand.getAddress())
                .registerDate(userRegisterCommand.getRegisterDate())
                .build();

    }

}
