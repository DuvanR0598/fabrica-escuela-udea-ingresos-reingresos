package co.edu.udea.fabrica_escuela.component.admisiones.domain.service;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.command.ExampleCommand;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.query.ExampleQuery;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.query.ExampleQuery2;
import co.edu.udea.fabrica_escuela.component.shared.domain.services.GenericServiceResponse;

public interface ExampleService {

    GenericServiceResponse doSomeTask(ExampleCommand exampleCommand);

    GenericServiceResponse findPaginated(ExampleQuery exampleQuery);

    GenericServiceResponse findById(ExampleQuery2 exampleQuery2);
}
