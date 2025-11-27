package com.acquarium.acquarium.exceptions;

import com.acquarium.acquarium.dtos.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponseDTO ErrorResponseDTO = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(ErrorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequestException(BadRequestException ex, WebRequest request) {
        ErrorResponseDTO ErrorResponseDTO = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(ErrorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ErrorResponseDTO ErrorResponseDTO = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(ErrorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(java.time.format.DateTimeParseException.class)
    public ResponseEntity<ErrorResponseDTO> handleDateTimeParseException(java.time.format.DateTimeParseException ex, WebRequest request) {
        ErrorResponseDTO ErrorResponseDTO = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "Formato data non valido: " + ex.getParsedString(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(ErrorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponseDTO ErrorResponseDTO = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(ErrorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
