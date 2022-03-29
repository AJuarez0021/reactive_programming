package com.atomic.controller;

import com.atomic.model.Courses;
import com.atomic.service.CoursesServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

/**
 * The Class CoursesController.
 *
 * @author ajuarez
 */
@RestController
@RequestMapping("/api")
@Api(value = "Servicio que realiza el CRUD de los cursos")
public class CoursesController {

    /** The service. */
    @Autowired
    private CoursesServices service;

    /**
     * List.
     *
     * @return the mono
     */
    @ApiOperation(value = "Servicio para listar los cursos", response = Courses[].class)
    @GetMapping(path = "/courses", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Mono<ResponseEntity<Flux<Courses>>> list() {
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll()));
    }

    /**
     * Listar por id.
     *
     * @param id the id
     * @return the mono
     */
    @ApiOperation(value = "Servicio para listar los cursos por id", response = Courses.class)
    @GetMapping(path = "/courses/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Mono<ResponseEntity<Courses>> listById(@PathVariable("id") Integer id) {
        validateParameter(id);
        return service.findById(id)
                .map(p -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Registrar.
     *
     * @param courses the courses
     * @param req the req
     * @return the mono
     */
    @ApiOperation(value = "Servicio para registrar los cursos", response = Courses.class)
    @PostMapping(path = "/courses/save", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Mono<ResponseEntity<Courses>> save(@Valid @RequestBody Courses courses,
            @ApiIgnore ServerHttpRequest req) {
        courses.setCoursesId(null);
        return service.save(courses)
                .map(p -> ResponseEntity.created(URI.create(req.getURI().toString()
                .concat("/")
                .concat(String.valueOf(p.getCoursesId()))))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p));
    }

    /**
     * Update.
     *
     * @param courses the courses
     * @return the mono
     */
    @ApiOperation(value = "Servicio para modificar los cursos", response = Courses.class)
    @PutMapping(path = "/courses/update", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Mono<ResponseEntity<Courses>> update(@Valid @RequestBody Courses courses) {
        Mono<Courses> monoCourses = Mono.just(courses);
        return service.findById(courses.getCoursesId())
                .flatMap(c -> monoCourses.map(u -> {
                    c.setName(u.getName());
                    c.setAcronym(u.getAcronym());
                    c.setStatus(u.isStatus());
                    return c;
                }))
                .flatMap(c -> service.update(c))
                .map(c -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
                
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the mono
     */
    @ApiOperation(value = "Servicio para eliminar un curso", response = Void.class)
    @DeleteMapping(path = "/courses/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Integer id) {
        validateParameter(id);
        return service.findById(id)
                .flatMap(p -> {
                    return service.delete(p.getCoursesId())
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Validate parameter.
     *
     * @param id the id
     */
    private void validateParameter(Integer id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The id " + id + " is not valid");
        }
    }
}
