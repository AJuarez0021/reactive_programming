package com.atomic.client.demo;

import com.atomic.client.dto.CoursesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import com.atomic.client.config.FeignCoursesClient;

/**
 * The Class FeignCoursesDemo.
 *
 * @author A. Juarez
 */
@Component
public class FeignCoursesDemo {

    /** The courses client. */
    @Autowired
    private FeignCoursesClient coursesClient;

    /**
     * Gets the all.
     *
     * @return the all
     */
    public Mono<Void> getAll() {
        return coursesClient.getAllCourses()
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
        CoursesDTO courseDto = CoursesDTO.builder()
                .acronym("MAT")
                .name("Matematicas")
                .status(true)
                .build();
        CoursesDTO c = coursesClient.saveCourse(courseDto)
                .doFinally(s -> System.out.println("------------- POST Course completed ------------------"))
                .block();
        return c == null ? 0 : c.getCoursesId();
    }

    /**
     * Gets the.
     *
     * @param id the id
     * @return the mono
     */
    public Mono<Void> get(Integer id) {
        return coursesClient.getCourse(id)
                .doOnNext(System.out::println)
                .doFinally(s -> System.out.println("------------- GET Course completed ------------------"))
                .then();
    }

    /**
     * Put.
     *
     * @param id the id
     * @return the mono
     */
    public Mono<Void> put(Integer id) {
        CoursesDTO courseDto = CoursesDTO.builder()
                .acronym("MATH")
                .name("Matematicas")
                .status(true)
                .coursesId(id)
                .build();
        return coursesClient.updateCourse(courseDto)
                .doOnNext(System.out::println)
                .doFinally(s -> System.out.println("------------- Course updated ------------------"))
                .then();
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the mono
     */
    public Mono<Void> delete(Integer id) {
        return coursesClient.deleteCourse(id)
                .doOnNext(System.out::println)
                .doFinally(s -> System.out.println("------------- Course deleted ------------------"))
                .then();
    }
}