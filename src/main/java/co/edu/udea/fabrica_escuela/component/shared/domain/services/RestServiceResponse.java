package co.edu.udea.fabrica_escuela.component.shared.domain.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestServiceResponse<T> {

    private T response;
    private HttpStatus statusCode;

    private RestServiceResponse(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public static <T> RestServiceResponse<T> of(HttpStatus statusCode, T object) {
        return new RestServiceResponse<T>(object, statusCode);
    }

    public static <T> RestServiceResponse<T> of(HttpStatus statusCode) {
        return new RestServiceResponse<T>(statusCode);
    }

}
