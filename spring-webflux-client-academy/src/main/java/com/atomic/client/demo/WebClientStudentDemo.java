package com.atomic.client.demo;

import com.atomic.client.config.WebClientStudent;
import com.atomic.client.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * The Class WebClientStudentDemo.
 *
 * @author ajuarez
 */
@Component
public class WebClientStudentDemo {

    /** The student client. */
    @Autowired
    private WebClientStudent studentClient;

    /**
     * Gets the all.
     *
     * @return the all
     */
    public Mono<Void> getAll() {
        return studentClient.getAllStudents()
                .doOnNext(System.out::println)
                .doFinally(s -> System.out.println("------------- GET All completed ------------------"))
                .then();
    }

    /**
     * Post.
     *
     * @return the int
     */
    public int post() {
        var studentDto = StudentDTO.builder()
                .age(11)
                .name("Atomic")
                .curp("AAAA11122AAA")
                .surnames("Labs")
                .build();
        StudentDTO c = studentClient.saveStudent(studentDto)
                .doFinally(s -> System.out.println("------------- POST Student completed ------------------"))
                .block();
        return c == null ? 0 : c.getStudentId();
    }

    /**
     * Gets the.
     *
     * @param id the id
     * @return the mono
     */
    public Mono<Void> get(Integer id) {
        return studentClient.getStudent(id)
                .doOnNext(System.out::println)
                .doFinally(s -> System.out.println("------------- GET Student completed ------------------"))
                .then();
    }

    /**
     * Put.
     *
     * @param id the id
     * @return the mono
     */
    public Mono<Void> put(Integer id) {
        var studentDto = StudentDTO.builder()
                .age(20)
                .name("Atomic")
                .curp("AAAA11122AAA")
                .surnames("Labs")
                .studentId(id)
                .build();
        return studentClient.updateStudent(studentDto)
                .doOnNext(System.out::println)
                .doFinally(s -> System.out.println("------------- Student updated ------------------"))
                .then();
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the mono
     */
    public Mono<Void> delete(Integer id) {
        return studentClient.deleteStudent(id)
                .doOnNext(System.out::println)
                .doFinally(s -> System.out.println("------------- Student deleted ------------------"))
                .then();
    }
}
