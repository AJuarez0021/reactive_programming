package com.atomic.client;

import com.atomic.client.demo.WebClientCoursesDemo;
import com.atomic.client.demo.FeignStudentDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactor.core.publisher.Flux;

/**
 * The Class SpringWebfluxClientAcademyApplication.
 */
@SpringBootApplication
@EnableReactiveFeignClients
public class SpringWebfluxClientAcademyApplication implements CommandLineRunner {

    /** The courses demo. */
    @Autowired
    private WebClientCoursesDemo coursesDemo;
    
    /** The student demo. */
    @Autowired
    private FeignStudentDemo studentDemo;

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxClientAcademyApplication.class, args);
    }

    /**
     * Run.
     *
     * @param args the args
     * @throws Exception the exception
     */
    @Override
    public void run(String... args) throws Exception {
        cursesDemo();
        studentDemo();
    }

    /**
     * Student demo.
     */
    private void studentDemo() {
        try {
            System.out.println("------------- Student Client -------------");
            int id = studentDemo.post();
            System.out.println("Id: " + id);
            Flux.concat(
                    studentDemo.getAll(),
                    studentDemo.get(id),
                    studentDemo.put(id),
                    studentDemo.get(id),
                    studentDemo.delete(id)
            ).subscribe();

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    /**
     * Curses demo.
     */
    private void cursesDemo() {
        try {
            System.out.println("------------- Courses Client -------------");
            int id = coursesDemo.post();
            System.out.println("Id: " + id);
            Flux.concat(
                    coursesDemo.getAll(),
                    coursesDemo.get(id),
                    coursesDemo.put(id),
                    coursesDemo.get(id),
                    coursesDemo.delete(id)
            ).subscribe();

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

}
