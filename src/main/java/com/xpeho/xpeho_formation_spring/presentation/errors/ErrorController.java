package com.xpeho.xpeho_formation_spring.presentation.errors;

import com.xpeho.xpeho_formation_spring.domain.errors.MovieNotFoundException;
import com.xpeho.xpeho_formation_spring.domain.errors.OmdbApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler({MovieNotFoundException.class})
    protected ResponseEntity<Object> handleMovieNotFound(RuntimeException ex, WebRequest request) {
        LOGGER.error("Movie not found for request {}", request, ex);
        String bodyOfResponse = "The movie was not found.";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({OmdbApiException.class})
    protected ResponseEntity<Object> handleOmdbApiException(RuntimeException ex, WebRequest request) {
        LOGGER.error("OMDb API unavailable for request {}", request, ex);
        String bodyOfResponse = "L'API OMDb est inaccessible.";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }
}
