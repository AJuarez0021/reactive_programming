package com.atomic.controller;

import com.atomic.model.Student;
import com.atomic.service.StudentServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.annotations.ApiIgnore;

/**
 * The Class StudentController.
 *
 * @author ajuarez
 */
@RestController
@RequestMapping("/api")
@Api(value = "Servicio que realiza el CRUD de los estudiantes")
public class StudentController {

    /** The service. */
    @Autowired
    private StudentServices service;


    /**
     * List.
     *
     * @return the mono
     */
    @ApiOperation(value = "Servicio para listar los estudiantes", response = Student[].class)
    @GetMapping(path = "/students", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Mono<ResponseEntity<Flux<Student>>> list() {
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll()));

    }

    /**
     * List by id.
     *
     * @param id the id
     * @return the mono
     */
    @ApiOperation(value = "Servicio para listar los estudiantes por id", response = Student.class)
    @GetMapping(path = "/students/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Mono<ResponseEntity<Student>> listById(@PathVariable("id") Integer id) {
        validateParameter(id);
        return service.findById(id)
                .map(p -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the mono
     */
    @ApiOperation(value = "Servicio para eliminar un estudiante", response = Void.class)
    @DeleteMapping("/students/delete/{id}")
    @ResponseBody
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Integer id) {
        validateParameter(id);
        return service.findById(id)
                .flatMap(p -> {
                    return service.delete(p.getStudentId())
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Save.
     *
     * @param student the student
     * @param req the req
     * @return the mono
     */
    @ApiOperation(value = "Servicio para registrar los estudiantes", response = Student.class)
    @PostMapping(path = "/students/save", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Mono<ResponseEntity<Student>> save(@RequestBody Student student, @ApiIgnore final ServerHttpRequest req) {
        student.setStudentId(null);
        return service.save(student)
                .map(p -> ResponseEntity.created(URI.create(req.getURI().toString()
                .concat("/")
                .concat(String.valueOf(p.getStudentId()))))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p));
    }

    /**
     * Update.
     *
     * @param student the student
     * @return the mono
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
     */
    @ApiOperation(value = "Servicio para modificar los estudiantes", response = Student.class)
    @PutMapping(path = "/students/update", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Mono<ResponseEntity<Student>> update(@RequestBody Student student) throws InterruptedException, ExecutionException {
        Mono<Student> monoStudent = Mono.just(student);
      
        return service.findById(student.getStudentId())
                .flatMap(s -> monoStudent.map(u -> {
                    s.setAge(u.getAge());
                    s.setCurp(u.getCurp());
                    s.setName(u.getName()) ;
                    s.setSurnames(u.getSurnames());
                    return s;
                }))
                .flatMap(s -> service.update(s)
                .map(p -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)))
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
