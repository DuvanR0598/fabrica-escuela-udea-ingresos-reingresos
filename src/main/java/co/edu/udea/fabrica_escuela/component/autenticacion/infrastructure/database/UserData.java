package co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.database;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            name = "last_name",
            length = 50,
            nullable = false
    )
    private String lastName;

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
            name = "phone_number",
            length = 30,
            nullable = false
    )
    private String phoneNumber;

    @NotBlank
    @Column(
            name = "password",
            length = 200,
            nullable = false
    )
    private String password;

    @NotBlank
    @Column(
            name = "address",
            nullable = false
    )
    private String address;

    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "detail_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleData> roles;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
