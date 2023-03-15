package ro.alexrmn.hospitalmanagerbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotAuthorizedException extends RuntimeException {
    private String message;
}
