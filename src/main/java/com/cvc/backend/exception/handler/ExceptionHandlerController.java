package com.cvc.backend.exception.handler;


import com.cvc.backend.common.model.ErrorResponse;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestControllerAdvice
public class ExceptionHandlerController {

    private final MessageSource messageSource;

    @Autowired
    public ExceptionHandlerController(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = BAD_REQUEST)
    public Collection<ErrorResponse> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(violation -> ErrorResponse.as(message(violation)).tag(violation.getField())).collect(Collectors.toList());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException ex) {
        return ErrorResponse.as(message("data.argument.type.mismatch"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadable(final HttpMessageNotReadableException ex) {

        if (ex.getRootCause() instanceof JsonMappingException) {
            return handleJsonMappingException((JsonMappingException) ex.getRootCause());
        }

        return ErrorResponse.as(message("unrecognized.structure"));
    }

    private ErrorResponse handleJsonMappingException(final JsonMappingException ex) {
        final String paramName = ex.getPath().stream()
                .map(ref -> Optional.ofNullable(ref.getFieldName()).orElse(String.format("[%s]", ref.getIndex())))
                .collect(Collectors.joining("."));

        return ErrorResponse
                .as(messageSource.getMessage("message.not.readable", new Object[]{paramName}, LocaleContextHolder.getLocale()))
                .tag(paramName);
    }

    private String message(final String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    private String message(final FieldError violation) {
        return messageSource.getMessage(violation, LocaleContextHolder.getLocale());
    }

    private String message(String code, Object... param) {
        return messageSource.getMessage(code, param, LocaleContextHolder.getLocale());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = BAD_REQUEST)
    public List<ErrorResponse> handleConstraintViolationException(final ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream().map(violation -> ErrorResponse.as(violation.getMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(HotelNoContentException.class)
    public ResponseEntity<String> handlerHotelNoContentException(final HotelNoContentException ex) {
        return ResponseEntity.status(NO_CONTENT).contentType(APPLICATION_JSON).build();

    }

    @ExceptionHandler(DateAfterCheckoutException.class)
    @ResponseStatus(code = CONFLICT)
    public ErrorResponse handleDateCheckinAfterDateCheckoutException() {
        return ErrorResponse.as(message("dateCheckiAfterDateCheckout"));
    }
}