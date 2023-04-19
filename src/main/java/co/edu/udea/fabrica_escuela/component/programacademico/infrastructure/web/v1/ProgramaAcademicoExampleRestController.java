package co.edu.udea.fabrica_escuela.component.programacademico.infrastructure.web.v1;

import co.edu.udea.fabrica_escuela.component.programacademico.domain.core.command.ProgramaAcademicoExampleCommand;
import co.edu.udea.fabrica_escuela.component.programacademico.domain.core.query.ProgramaAcademicoExampleQuery;
import co.edu.udea.fabrica_escuela.component.programacademico.domain.service.ProgramaAcademicoExampleService;
import co.edu.udea.fabrica_escuela.component.programacademico.infrastructure.web.v1.model.request.ProgramaAcademicoExampleRequestDto;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.web.v1.interfaces.RestControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/example")
@RequiredArgsConstructor
public class ProgramaAcademicoExampleRestController implements RestControllerUtils {

    private final ProgramaAcademicoExampleService programaAcademicoExampleService;

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> exampleCreate(@RequestBody ProgramaAcademicoExampleRequestDto programaAcademicoExampleRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.getServerErrorResponse(bindingResult);
        }
        ProgramaAcademicoExampleCommand programaAcademicoExampleCommand =
                ProgramaAcademicoExampleRequestDto.toCommand(programaAcademicoExampleRequestDto);
        return null;
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<?> exampleFindPaginated(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        ProgramaAcademicoExampleQuery programaAcademicoExampleQuery = ProgramaAcademicoExampleQuery.builder()
                .build();
        return null;
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @GetMapping(path = "/{entityId}", produces = "application/json")
    public ResponseEntity<?> exampleFindById(
            @PathVariable("entityId") Long entityId) {
        return null;
    }

}
