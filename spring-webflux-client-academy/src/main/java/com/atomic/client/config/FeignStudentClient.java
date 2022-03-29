package com.atomic.client.config;


import com.atomic.client.dto.StudentDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The Interface FeignStudentClient.
 *
 * @author A. Juarez
 */
@ReactiveFeignClient(value = "student-service", url = "${academy.service.url.v2}")
public interface FeignStudentClient {
    
    /**
     * Gets the all students.
     *
     * @return the all students
     */
    @GetMapping("/students")
    Flux<StudentDTO> getAllStudents();

    /**
     * Gets the student.
     *
     * @param studentId the student id
     * @return the student
     */
    @GetMapping("/students/{id}")
    Mono<StudentDTO> getStudent(@PathVariable("id") Integer studentId);

    /**
     * Save student.
     *
     * @param studentDto the student dto
     * @return the mono
     */
    @PostMapping("/students/save")
    Mono<StudentDTO> saveStudent(StudentDTO studentDto);

    /**
     * Update student.
     *
     * @param studentDto the student dto
     * @return the mono
     */
    @PutMapping("/students/update")
    Mono<StudentDTO> updateStudent(StudentDTO studentDto);

    /**
     * Delete student.
     *
     * @param studentId the student id
     * @return the mono
     */
    @DeleteMapping("/students/delete/{id}")
    Mono<Void> deleteStudent(@PathVariable("id") Integer studentId);
}
