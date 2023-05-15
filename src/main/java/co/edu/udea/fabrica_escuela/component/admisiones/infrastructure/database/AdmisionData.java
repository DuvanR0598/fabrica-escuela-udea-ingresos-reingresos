package co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "admisiones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class AdmisionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
