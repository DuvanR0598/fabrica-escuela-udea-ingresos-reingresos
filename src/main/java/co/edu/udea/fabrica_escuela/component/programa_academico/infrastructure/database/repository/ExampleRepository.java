package co.edu.udea.fabrica_escuela.component.programa_academico.infrastructure.database.repository;

import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.database.ExampleEntityData;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExampleRepository extends JpaRepository<ExampleEntityData, Long> {

    List<ExampleEntityData> findAll(PageRequest pageRequest);
}
