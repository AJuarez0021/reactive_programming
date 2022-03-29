package com.atomic.handler;

import com.atomic.model.Student;
import com.atomic.service.StudentServices;
import com.atomic.util.RequestValidator;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * The Class StudentHandler.
 *
 * @author ajuarez
 */
@Component
public class StudentHandler {
    
    /** The validator. */
    @Autowired
    private RequestValidator validator;
    
    /** The service. */
    @Autowired
    private StudentServices service;
    
    
    /**
     * List.
     *
     * @param req the req
     * @return the mono
     */
    public Mono<ServerResponse> list(ServerRequest req) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Student.class);
    }

    /**
     * List by id.
     *
     * @param req the req
     * @return the mono
     */
    public Mono<ServerResponse> listById(ServerRequest req) {
        String id = req.pathVariable("id");
        return service.findById(Integer.parseInt(id))
                .flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(p)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * Save.
     *
     * @param req the req
     * @return the mono
     */
    public Mono<ServerResponse> save(ServerRequest req) {
        Mono<Student> monoStudent = req.bodyToMono(Student.class);
        return monoStudent.flatMap(validator::validate)
                .flatMap(service::save)
                .flatMap(p -> ServerResponse.created(URI.create(req.uri().toString()
                .concat("/")
                .concat(String.valueOf(p.getStudentId()))))
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(p)));
    }

    /**
     * Update.
     *
     * @param req the req
     * @return the mono
     */
    public Mono<ServerResponse> update(ServerRequest req) {
        Mono<Student> monoStudent = req.bodyToMono(Student.class);
        return monoStudent.flatMap(validator::validate)
                .flatMap(service::update)
                .flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(p)));
    }

    /**
     * Delete.
     *
     * @param req the req
     * @return the mono
     */
    public Mono<ServerResponse> delete(ServerRequest req) {
        String id = req.pathVariable("id");
        return service.findById(Integer.parseInt(id))
                .flatMap(p -> service.delete(p.getStudentId())
                .then(ServerResponse.noContent().build())
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
