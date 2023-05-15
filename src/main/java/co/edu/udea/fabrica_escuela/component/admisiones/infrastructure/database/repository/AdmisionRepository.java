package co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.repository;

import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.AdmisionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmisionRepository  extends JpaRepository<AdmisionData, Long> {
}
