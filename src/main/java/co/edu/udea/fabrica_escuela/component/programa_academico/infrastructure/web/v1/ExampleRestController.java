package co.edu.udea.fabrica_escuela.component.programa_academico.infrastructure.web.v1;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.command.ExampleCommand;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.query.ExampleQuery;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.query.ExampleQuery2;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.service.ExampleService;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.web.v1.model.request.ExampleRequestDto;
import co.edu.udea.fabrica_escuela.component.shared.domain.services.GenericServiceResponse;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.web.v1.interfaces.RestControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/example")
@RequiredArgsConstructor
public class ExampleRestController implements RestControllerUtils {

    private final ExampleService exampleService;

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> exampleCreate(@RequestBody ExampleRequestDto exampleRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.getServerErrorResponse(bindingResult);
        }
        ExampleCommand exampleCommand =
                ExampleRequestDto.toCommand(exampleRequestDto);
        GenericServiceResponse response = this.exampleService.doSomeTask(exampleCommand);
        return getResponseEntity(response);
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<?> exampleFindPaginated(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        ExampleQuery exampleQuery = ExampleQuery.builder()
                .page(page)
                .size(size)
                .build();
        GenericServiceResponse response = this.exampleService.findPaginated(exampleQuery);

        return getResponseEntity(response);
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @GetMapping(path = "/{entityId}", produces = "application/json")
    public ResponseEntity<?> exampleFindById(
            @PathVariable("entityId") Long entityId) {
        ExampleQuery2 exampleQuery2 = ExampleQuery2.builder()
                .id(entityId)
                .build();
        GenericServiceResponse response =
                this.exampleService.findById(exampleQuery2);

        return getResponseEntity(response);
    }

}
