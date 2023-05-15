package co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.web.v1;

import co.edu.udea.fabrica_escuela.component.admisiones.domain.core.command.AdmisionRegisterCommand;
import co.edu.udea.fabrica_escuela.component.admisiones.domain.services.AdmisionesService;
import co.edu.udea.fabrica_escuela.component.admisiones.infrastructure.web.v1.model.request.AdmisionRegisterRequestDto;
import co.edu.udea.fabrica_escuela.component.autenticacion.domain.core.command.UserRegisterCommand;
import co.edu.udea.fabrica_escuela.component.autenticacion.infrastructure.web.v1.model.request.UserRegisterRequestDto;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.web.v1.interfaces.RestControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admisiones")
@RequiredArgsConstructor
public class AdmisionesController implements RestControllerUtils {

    private final AdmisionesService admisionesService;

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AdmisionRegisterRequestDto admisionRegisterRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return getServerErrorResponse(bindingResult);
        try {
            AdmisionRegisterCommand command = AdmisionRegisterRequestDto
                    .toRegisterAdmisionCommand(admisionRegisterRequestDto);
            var registerResponse = this.admisionesService.registerAdmision(command);
            return ResponseEntity.status(registerResponse.getStatusCode())
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
