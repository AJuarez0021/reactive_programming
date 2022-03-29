package com.atomic.handler;

import com.atomic.model.Courses;
import com.atomic.service.CoursesServices;
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
 * The Class CourseHandler.
 *
 * @author ajuarez
 */
@Component
public class CourseHandler {

    /** The service. */
    @Autowired
    private CoursesServices service;

    /** The validator. */
    @Autowired
    private RequestValidator validator;

    /**
     * List.
     *
     * @param req the req
     * @return the mono
     */
    public Mono<ServerResponse> list(ServerRequest req) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Courses.class);
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
        Mono<Courses> monoCourses = req.bodyToMono(Courses.class);
        return monoCourses.flatMap(validator::validate)
                .flatMap(service::save)
                .flatMap(p -> ServerResponse.created(URI.create(req.uri().toString()
                .concat("/")
                .concat(String.valueOf(p.getCoursesId()))))
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
        Mono<Courses> monoCourses = req.bodyToMono(Courses.class);
        return monoCourses.flatMap(validator::validate)
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
                .flatMap(p -> service.delete(p.getCoursesId())
                .then(ServerResponse.noContent().build())
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
