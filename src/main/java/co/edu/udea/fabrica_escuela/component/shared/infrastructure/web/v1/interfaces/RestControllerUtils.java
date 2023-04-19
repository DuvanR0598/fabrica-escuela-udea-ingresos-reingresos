package co.edu.udea.fabrica_escuela.component.shared.infrastructure.web.v1.interfaces;

import co.edu.udea.fabrica_escuela.component.shared.domain.services.GenericServiceResponse;
import co.edu.udea.fabrica_escuela.component.shared.infrastructure.web.v1.model.response.GenericServerResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

public interface RestControllerUtils {

    @SuppressWarnings("DataFlowIssue")
    default Map<String, String> getErrorsFromBindingResult(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
    }

    private ResponseEntity<Map<String, String>> getServerResponseErrorEntity(BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(this.getErrorsFromBindingResult(bindingResult));
    }

    default ResponseEntity<GenericServerResponse> getServerErrorResponse(BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(GenericServerResponse.builder()
                        .ok(false)
                        .response(this.getErrorsFromBindingResult(bindingResult))
                        .build());
    }


    default ResponseEntity<GenericServerResponse> getResponseEntity(GenericServiceResponse response) {
        return ResponseEntity.status(HttpStatus.valueOf(response.getStatusCode()))
                .body(GenericServerResponse.builder()
                        .ok(response.isOk())
                        .response(response.getMessage())
                        .build());
    }


}
