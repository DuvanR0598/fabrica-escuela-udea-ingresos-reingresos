package co.edu.udea.fabrica_escuela.component.programa_academico.domain.service;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.command.ExampleCommand;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.query.ExampleQuery;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.query.ExampleQuery2;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.service.ExampleService;
import co.edu.udea.fabrica_escuela.component.shared.domain.services.GenericServiceResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder(toBuilder = true)
public class ExampleServiceImpl implements ExampleService {

    @Override
    public GenericServiceResponse doSomeTask(ExampleCommand exampleCommand) {
        return null;
    }

    @Override
    public GenericServiceResponse findPaginated(ExampleQuery exampleQuery) {
        return null;
    }

    @Override
    public GenericServiceResponse findById(ExampleQuery2 exampleQuery2) {
        return null;
    }
}