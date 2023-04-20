package co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.repository;

import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.AdmisionesExampleEntityData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmisionesExampleRepository extends JpaRepository<AdmisionesExampleEntityData, Long> {

}
