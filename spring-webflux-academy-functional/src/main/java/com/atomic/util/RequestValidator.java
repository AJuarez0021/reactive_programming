package com.atomic.util;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * The Class RequestValidator.
 *
 * @author ajuarez
 */
@Component
public class RequestValidator {

    /**
     * The validador.
     */
    @Autowired
    private Validator validador;

    /**
     * Validate.
     *
     * @param <T> the generic type
     * @param obj the obj
     * @return the mono
     */
    public <T> Mono<T> validate(T obj) {

        if (obj == null) {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
        }

        Set<ConstraintViolation<T>> violations = this.validador.validate(obj);
        if (violations == null || violations.isEmpty()) {
            return Mono.just(obj);
        }

        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
}
