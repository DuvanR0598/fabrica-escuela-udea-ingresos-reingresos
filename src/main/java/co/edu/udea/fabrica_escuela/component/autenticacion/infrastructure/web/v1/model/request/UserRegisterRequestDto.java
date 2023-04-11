package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.request;

import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.PreRegister;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserRegisterCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.query.ExampleQuery;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.query.FindByEmailQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserRegisterRequestDto {

    @NotEmpty
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

    // country | Street Address | Street Address Line 2 | City | State or Province | Postal or Zip Code
    @NotNull
    @Length(min = 5, max = 200)
    private String address;

    public static ExampleQuery toEmailExistenceQuery(UserRegisterRequestDto userRegisterRequestDto) {
        return ExampleQuery.builder()
                .email(userRegisterRequestDto.getEmail())
                .build();
    }

    public static FindByEmailQuery toFindByEmailQuery(UserRegisterRequestDto userRegisterRequestDto) {
        return FindByEmailQuery.builder()
                .email(userRegisterRequestDto.getEmail())
                .build();
    }

    public static UserRegisterCommand toRegisterAdminCommand(UserRegisterRequestDto userRegisterRequestDto) {
        return UserRegisterCommand.builder()
                .username(userRegisterRequestDto.getEmail().split("@")[0].trim())
                .firstName(userRegisterRequestDto.getFirstName())
                .lastName(userRegisterRequestDto.getLastName())
                .email(userRegisterRequestDto.getEmail())
                .phoneNumber(userRegisterRequestDto.getPhoneNumber())
                .password(userRegisterRequestDto.getPassword())
                .address(userRegisterRequestDto.getAddress())
                .registerDate(LocalDate.now())
                .build();
    }

    public static UserRegisterCommand toRegisterUserCommand(UserRegisterRequestDto userRegisterRequestDto, PreRegister preRegister) {
        return UserRegisterCommand.builder()
                .username(userRegisterRequestDto.getEmail().split("@")[0].trim())
                .firstName(userRegisterRequestDto.getFirstName())
                .lastName(userRegisterRequestDto.getLastName())
                .email(userRegisterRequestDto.getEmail())
                .phoneNumber(userRegisterRequestDto.getPhoneNumber())
                .password(userRegisterRequestDto.getPassword())
                .address(userRegisterRequestDto.getAddress())
                .registerDate(LocalDate.now())
                .roles(Set.of(preRegister.getEnumRole()))
                .build();
    }

}
