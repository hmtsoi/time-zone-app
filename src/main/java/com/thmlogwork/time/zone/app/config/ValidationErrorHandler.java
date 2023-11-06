package com.thmlogwork.time.zone.app.config;

import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ValidationErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        var response = new ValidationErrorResponse(ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), e.getMessage()));
        return ResponseEntity.badRequest().body(response);
    }

    @RequiredArgsConstructor
    @Getter
    static class ValidationErrorResponse implements ErrorResponse {

        private final ProblemDetail body;

        @Override
        public HttpStatusCode getStatusCode() {
            return HttpStatusCode.valueOf(body.getStatus());
        }
    }
}
