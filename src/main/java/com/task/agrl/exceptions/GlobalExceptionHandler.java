package com.task.agrl.exceptions;

import com.task.agrl.models.entity.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Response<?>> handlePostBusinessException(BusinessException ex) {
        Response<?> errorResponse = new Response<>();
        errorResponse.setStatus("ERROR");
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getHttpEstatus());
    }
}

