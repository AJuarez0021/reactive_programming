package com.atomic.client.config;

import com.atomic.client.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The Class WebClientStudent.
 *
 * @author ajuarez
 */
@Component
public class WebClientStudent {

    /** The web client. */
    @Autowired
    private WebClient webClient;

    /**
     * Gets the client.
     *
     * @return the client
     */
    private WebClient getClient() {
        return webClient;
    }

    /**
     * Gets the all students.
     *
     * @return the all students
     */
    public Flux<StudentDTO> getAllStudents() {
        return getClient()
                .get()
                .uri("/students")
                .retrieve()
                .bodyToFlux(StudentDTO.class);
    }

    /**
     * Gets the student.
     *
     * @param id the id
     * @return the student
     */
    public Mono<StudentDTO> getStudent(int id) {
        return getClient()
                .get()
                .uri(String.format("/students/%d", id))
                .retrieve()
                .bodyToMono(StudentDTO.class);
    }

    /**
     * Save student.
     *
     * @param studentDto the student dto
     * @return the mono
     */
    public Mono<StudentDTO> saveStudent(StudentDTO studentDto) {
        return getClient()
                .post()
                .uri("/students/save")
                .body(Mono.just(studentDto), StudentDTO.class)
                .retrieve()
                .bodyToMono(StudentDTO.class);
    }

    /**
     * Update student.
     *
     * @param studentDto the student dto
     * @return the mono
     */
    public Mono<StudentDTO> updateStudent(StudentDTO studentDto) {
        return getClient()
                .put()
                .uri("/students/update")
                .body(Mono.just(studentDto), StudentDTO.class)
                .retrieve()
                .bodyToMono(StudentDTO.class);
    }

    /**
     * Delete student.
     *
     * @param id the id
     * @return the mono
     */
    public Mono<Void> deleteStudent(int id) {
        return getClient()
                .delete()
                .uri(String.format("/students/delete/%d", id))
                .retrieve()
                .bodyToMono(Void.class);
    }
}
