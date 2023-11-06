package com.thmlogwork.time.zone.app.rest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

class CommaSeparatedLatLngValidator implements ConstraintValidator<CommaSeparatedLatLng, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final String[] arr = value.split(",");
        if (arr.length != 2) {
            context.unwrap(HibernateConstraintValidatorContext.class).addMessageParameter(
                    "message",
                    "Please input longitude and latitude comma separated in form of {latitude},{longitude}");
            return false;
        }
        final var latitude = Double.parseDouble(arr[0]);
        final var longitude = Double.parseDouble(arr[1]);
        if (latitude > 90 || latitude < -90) {
            context.unwrap(HibernateConstraintValidatorContext.class).addMessageParameter(
                    "message",
                    "Latitude should be between -90 and 90");
            return false;
        }
        if (longitude > 180 || longitude < -180) {
            context.unwrap(HibernateConstraintValidatorContext.class).addMessageParameter(
                    "message",
                    "Longitude should be between -180 and 180");
            return false;
        }
        return true;
    }
}
