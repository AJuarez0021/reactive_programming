package com.atomic.client.config;

import com.atomic.client.dto.CoursesDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The Interface FeignCoursesClient.
 *
 * @author A. Juarez
 */
@ReactiveFeignClient(value = "course-service", url = "${academy.service.url.v2}")
public interface FeignCoursesClient {

    /**
     * Gets the all courses.
     *
     * @return the all courses
     */
    @GetMapping("/courses")
    Flux<CoursesDTO> getAllCourses();

    /**
     * Gets the course.
     *
     * @param id the id
     * @return the course
     */
    @GetMapping("/courses/{id}")
    Mono<CoursesDTO> getCourse(@PathVariable("id") Integer id);

    /**
     * Save course.
     *
     * @param courseDto the course dto
     * @return the mono
     */
    @PostMapping("/courses/save")
    Mono<CoursesDTO> saveCourse(CoursesDTO courseDto);

    /**
     * Update course.
     *
     * @param courseDto the course dto
     * @return the mono
     */
    @PutMapping("/courses/update")
    Mono<CoursesDTO> updateCourse(CoursesDTO courseDto);

    /**
     * Delete course.
     *
     * @param id the id
     * @return the mono
     */
    @DeleteMapping("/courses/delete/{id}")
    Mono<Void> deleteCourse(@PathVariable("id") Integer id);
}
