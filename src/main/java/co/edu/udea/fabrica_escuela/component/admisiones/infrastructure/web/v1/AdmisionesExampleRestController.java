package co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.web.v1;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.command.AdmisionesExampleCommand;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.query.AdmisionesExampleQuery;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.service.AdmisionesExampleService;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.web.v1.model.request.AdmisionesExampleRequestDto;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.web.v1.interfaces.RestControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/example")
@RequiredArgsConstructor
public class AdmisionesExampleRestController implements RestControllerUtils {

    private final AdmisionesExampleService admisionesExampleService;

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> exampleCreate(@RequestBody AdmisionesExampleRequestDto admisionesExampleRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.getServerErrorResponse(bindingResult);
        }
        AdmisionesExampleCommand admisionesExampleCommand =
                AdmisionesExampleRequestDto.toCommand(admisionesExampleRequestDto);
        return null;
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<?> exampleFindPaginated(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        AdmisionesExampleQuery admisionesExampleQuery = AdmisionesExampleQuery.builder()
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
