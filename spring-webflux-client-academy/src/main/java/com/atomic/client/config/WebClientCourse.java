package com.atomic.client.config;

import com.atomic.client.dto.CoursesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The Class WebClientCourse.
 *
 * @author ajuarez
 */
@Component
public class WebClientCourse {

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
     * Gets the all courses.
     *
     * @return the all courses
     */
    public Flux<CoursesDTO> getAllCourses() {
        return getClient()
                .get()
                .uri("/courses")
                .retrieve()
                .bodyToFlux(CoursesDTO.class);
    }

    /**
     * Gets the course.
     *
     * @param id the id
     * @return the course
     */
    public Mono<CoursesDTO> getCourse(int id) {
        return getClient()
                .get()
                .uri(String.format("/courses/%d", id))
                .retrieve()
                .bodyToMono(CoursesDTO.class);
    }

    /**
     * Save course.
     *
     * @param courseDto the course dto
     * @return the mono
     */
    public Mono<CoursesDTO> saveCourse(CoursesDTO courseDto) {
        return getClient()
                .post()
                .uri("/courses/save")
                .body(Mono.just(courseDto), CoursesDTO.class)
                .retrieve()
                .bodyToMono(CoursesDTO.class);
    }

    /**
     * Update course.
     *
     * @param courseDto the course dto
     * @return the mono
     */
    public Mono<CoursesDTO> updateCourse(CoursesDTO courseDto) {
        return getClient()
                .put()
                .uri("/courses/update")
                .body(Mono.just(courseDto), CoursesDTO.class)
                .retrieve()
                .bodyToMono(CoursesDTO.class);
    }

    /**
     * Delete course.
     *
     * @param id the id
     * @return the mono
     */
    public Mono<Void> deleteCourse(int id) {
        return getClient()
                .delete()
                .uri(String.format("/courses/delete/%d", id))
                .retrieve()
                .bodyToMono(Void.class);
    }
}
