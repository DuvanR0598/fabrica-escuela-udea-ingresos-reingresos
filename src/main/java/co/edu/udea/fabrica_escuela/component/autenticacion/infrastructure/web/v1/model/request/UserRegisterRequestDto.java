package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.request;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.Role;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserRegisterCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserRegisterRequestDto {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@udea\\.edu\\.co$", message = "Email not allowed")
    private String email;

    @NotNull
    @Length(min = 3, max = 50)
    private String firstName;

    @NotNull
    @Length(min = 3, max = 50)
    private String lastName;

    @NotNull
    @Length(min = 5, max = 50)
    private String phoneNumber;

    @NotNull
    @Length(min = 8, max = 20)
    private String password;

    @NotNull
    @Length(min = 5, max = 200)
    private String address;

    public static UserRegisterCommand toRegisterUserCommand(UserRegisterRequestDto userRegisterRequestDto) {
        return UserRegisterCommand.builder()
                .username(userRegisterRequestDto.getEmail().split("@")[0].trim())
                .firstName(userRegisterRequestDto.getFirstName())
                .lastName(userRegisterRequestDto.getLastName())
                .email(userRegisterRequestDto.getEmail())
                .phoneNumber(userRegisterRequestDto.getPhoneNumber())
                .password(userRegisterRequestDto.getPassword())
                .address(userRegisterRequestDto.getAddress())
                .build();
    }

}
