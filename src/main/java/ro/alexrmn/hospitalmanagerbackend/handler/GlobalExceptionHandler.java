package ro.alexrmn.hospitalmanagerbackend.handler;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.alexrmn.hospitalmanagerbackend.exception.ObjectNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseEntity<?> handleException(ObjectNotValidException exception){
        System.out.println(exception.getMessage());
        return ResponseEntity.badRequest()
                .body(exception.getErrorMessages());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleException(EntityNotFoundException exception){
        System.out.println(exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<?> handleException(EntityExistsException exception){
        System.out.println(exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
