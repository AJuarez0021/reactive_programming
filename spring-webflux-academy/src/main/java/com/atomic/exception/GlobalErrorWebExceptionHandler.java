package com.atomic.exception;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * The Class GlobalErrorWebExceptionHandler.
 *
 * @author ajuarez
 */
@Component
@Order(-1)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    /**
     * Instantiates a new global error web exception handler.
     *
     * @param errorAttributes the error attributes
     * @param resources the resources
     * @param applicationContext the application context
     * @param configurer the configurer
     */
    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes,
            Resources resources,
            ApplicationContext applicationContext,
            ServerCodecConfigurer configurer) {
        super(errorAttributes, resources, applicationContext);
        setMessageWriters(configurer.getWriters());
    }

    /**
     * Gets the routing function.
     *
     * @param errorAttributes the error attributes
     * @return the routing function
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * Render error response.
     *
     * @param request the request
     * @return the mono
     */
    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {

        Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.
                of(ErrorAttributeOptions.Include.EXCEPTION));

        final Map<String, Object> mapException = new HashMap<>();

        HttpStatus httpStatus;
        var statusCode = String.valueOf(errorPropertiesMap.get("status"));
        Throwable error = getError(request);
        var timestamp = Timestamp.from(Instant.now());
        switch (statusCode) {
            case "500":
                mapException.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
                mapException.put("exception", error.getMessage());
                mapException.put("timestamp", timestamp.toString());
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            case "400":
                mapException.put("status", HttpStatus.BAD_REQUEST.value());
                if (error instanceof WebExchangeBindException) {
                    WebExchangeBindException resp = (WebExchangeBindException) error;
                    mapException.put("exception", resp.getMessage());
                } else if (error instanceof ResponseStatusException) {
                    ResponseStatusException resp = (ResponseStatusException) error;
                    mapException.put("exception", resp.getMessage());
                } else {
                    mapException.put("exception", "Incorrect request");
                }
                mapException.put("timestamp", timestamp.toString());
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                mapException.put("status", statusCode);
                mapException.put("timestamp", timestamp.toString());
                if (error instanceof Exception) {
                    Exception ex = (Exception) error;
                    mapException.put("exception", ex.getMessage());
                } else {
                    mapException.put("exception", "Unknown error ");
                }
                httpStatus = HttpStatus.CONFLICT;
                break;
        }

        return ServerResponse.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(mapException));

    }
}