package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(
            name = "username",
            unique = true,
            updatable = false,
            length = 50,
            nullable = false
    )
    private String username;

    @NotBlank
    @Column(
            name = "first_name",
            length = 50,
            nullable = false
    )
    private String firstName;

    @Column(
            name = "middle_name",
            length = 50
    )
    private String middleName;

    @NotBlank
    @Column(
            name = "first_lastname",
            length = 50,
            nullable = false
    )
    private String firstLastname;

    @Column(
            name = "second_lastname",
            length = 50
    )
    private String secondLastname;

    @NotBlank
    @Column(
            name = "email",
            unique = true,
            updatable = false,
            length = 100,
            nullable = false
    )
    private String email;

    @NotBlank
    @Column(
            name = "password",
            length = 300,
            nullable = false
    )
    private String password;

    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "detail_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleData> roles;

}
