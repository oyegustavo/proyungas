package ar.org.proyungas.usuarios.shared.infrastructure.output.restclient;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(basePackages = "ar.org.proyungas.usuarios")
public class ErrorHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handle(Throwable ex) {
        ex.printStackTrace();
        return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex) {
        return buildResponseError(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST_ERROR, ex);
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<ErrorResponse> handle(DataAccessException ex) {
        log.error("Database error", ex);
        return buildResponseError(HttpStatus.SERVICE_UNAVAILABLE, ErrorCode.DATABASE_ERROR);
    }

    @ExceptionHandler(DatabaseConnectionException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<ErrorResponse> handle(DatabaseConnectionException ex) {
        log.error("Database error", ex);
        return buildResponseError(HttpStatus.SERVICE_UNAVAILABLE, ErrorCode.DATABASE_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentTypeMismatchException ex) {
        return buildResponseError(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handle(HttpRequestMethodNotSupportedException ex) {
        return buildResponseError(HttpStatus.METHOD_NOT_ALLOWED, ErrorCode.BAD_REQUEST_ERROR);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handle(RoleNotFoundException ex) {
        return buildResponseError(HttpStatus.NOT_FOUND, ErrorCode.ROLE_NOT_FOUND_ERROR);
    }
    
    @ExceptionHandler(InvalidFilterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handle(InvalidFilterException ex) {
        return buildResponseError(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_FILTER);
    }
    
    @ExceptionHandler(InvalidFilterTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handle(InvalidFilterTypeException ex) {
        return buildResponseError(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_FILTER_TYPE_ERROR);
    }
    
    @ExceptionHandler(UserBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handle(UserBadRequestException ex) {
        return buildResponseError(HttpStatus.BAD_REQUEST, ErrorCode.USER_BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handle(InvalidUserException ex) {
        return buildResponseError(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_USER_ERROR);
    }
    
    @ExceptionHandler(MalformedFilterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handle(MalformedFilterException ex) {
        return buildResponseError(HttpStatus.BAD_REQUEST, ErrorCode.MALFORMED_FILTER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildResponseError(
            HttpStatus httpStatus,
            ErrorCode errorCode,
            MethodArgumentNotValidException ex) {

        ArrayList<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName.concat(": ").concat(errorMessage));
        });

        final var response = ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(String.join(", ", errors))
                .build();

        return new ResponseEntity<>(response, httpStatus);
    }

    private ResponseEntity<ErrorResponse> buildResponseError(HttpStatus httpStatus, ErrorCode errorCode) {
        final var response = ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getReason())
                .build();

        return new ResponseEntity<>(response, httpStatus);
    }

    private ResponseEntity<ErrorResponse> buildResponseError(HttpStatus httpStatus, String message) {
        final var response = ErrorResponse.builder()
                .message(message)
                .build();

        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        String message = "Invalid request payload";

        if (cause instanceof JsonMappingException && cause.getCause() != null) {
            message = cause.getCause().getMessage();
        } else if (cause != null && cause.getMessage() != null) {
            message = cause.getMessage();
        }
        
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("code", "BAD_REQUEST");
        errorMap.put("message", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }
    
    @Builder
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static class ErrorResponse {

        @JsonProperty
        String code;
        @JsonProperty
        String message;
    }
}
