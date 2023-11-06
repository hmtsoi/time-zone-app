package com.thmlogwork.time.zone.app.rest;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CommaSeparatedLatLngValidator.class)
@Target({PARAMETER})
@Retention(RUNTIME)
@Documented
public @interface CommaSeparatedLatLng {
    String message() default "{message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
