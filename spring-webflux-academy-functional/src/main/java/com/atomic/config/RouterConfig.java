package com.atomic.config;

import com.atomic.handler.CourseHandler;
import com.atomic.handler.StudentHandler;
import com.atomic.model.Courses;
import com.atomic.model.Student;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import org.springframework.web.reactive.function.server.RouterFunction;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * The Class RouterConfig.
 *
 * @author ajuarez
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "ACADEMY - REST SERVICE WebFlux Api Documentation", version = "1.0", description = "SERVICIO REST WebFlux Api Documentation"))
public class RouterConfig {

    /**
     * Paths courses.
     *
     * @param handler the handler
     * @return the router function
     */
    @RouterOperations({
        @RouterOperation(path = "/api/v2/courses", 
                beanClass = CourseHandler.class, 
                beanMethod = "list", 
                operation = @Operation(operationId = "list", summary = "Servicio para listar los cursos", tags = { "Courses" },
                        responses = { @ApiResponse( 
                                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Courses.class))),
                                responseCode = "200", 
                                description = "Sucess") }), 
                produces = {MediaType.APPLICATION_JSON_VALUE}),
               
        @RouterOperation(path = "/api/v2/courses/{id}", beanClass = CourseHandler.class, beanMethod = "listById", 
                operation = @Operation(operationId = "listById", summary = "Servicio para listar los cursos por id", tags = { "Courses" },
                        parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "Id") }, 
                        responses = { @ApiResponse( content = @Content(schema = @Schema(implementation = Courses.class)),
                                responseCode = "200", 
                                description = "Sucess") }),
                produces = {MediaType.APPLICATION_JSON_VALUE}),
        @RouterOperation(path = "/api/v2/courses/save", beanClass = CourseHandler.class, beanMethod = "save", 
                operation = @Operation(operationId = "save", summary = "Servicio para registrar los cursos", tags = { "Courses" },
                       requestBody = @RequestBody(required = true, description = "Request", content = @Content(schema = @Schema(implementation = Courses.class))) ,
                        responses = { @ApiResponse( content = @Content(schema = @Schema(implementation = Courses.class)),
                                responseCode = "200", 
                                description = "Sucess") }),
                produces = {MediaType.APPLICATION_JSON_VALUE}),
        @RouterOperation(path = "/api/v2/courses/update", beanClass = CourseHandler.class, beanMethod = "update", 
                 operation = @Operation(operationId = "update", summary = "Servicio para modificar los cursos", tags = { "Courses" },
                       requestBody = @RequestBody(required = true, description = "Request", content = @Content(schema = @Schema(implementation = Courses.class))) ,
                        responses = { @ApiResponse( content = @Content(schema = @Schema(implementation = Courses.class)),
                                responseCode = "200", 
                                description = "Sucess") }),
                produces = {MediaType.APPLICATION_JSON_VALUE}),
        @RouterOperation(path = "/api/v2/courses/delete/{id}", beanClass = CourseHandler.class, beanMethod = "delete", 
                operation = @Operation(operationId = "delete", summary = "Servicio para eliminar un curso", tags = { "Courses" },
                        parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "Id") }, 
                        responses = { @ApiResponse( content = @Content(schema = @Schema(implementation = Void.class)),
                                responseCode = "200", 
                                description = "Sucess") }),
                produces = {MediaType.APPLICATION_JSON_VALUE})})
    @Bean
    public RouterFunction<ServerResponse> pathsCourses(CourseHandler handler) {
        return route(GET("/api/v2/courses"), handler::list)
                .andRoute(GET("/api/v2/courses/{id}"), handler::listById)
                .andRoute(POST("/api/v2/courses/save"), handler::save)
                .andRoute(PUT("/api/v2/courses/update"), handler::update)
                .andRoute(DELETE("/api/v2/courses/delete/{id}"), handler::delete);
    }

    /**
     * Paths students.
     *
     * @param handler the handler
     * @return the router function
     */
    @RouterOperations({
        @RouterOperation(path = "/api/v2/students", beanClass = StudentHandler.class, beanMethod = "list", 
                operation = @Operation(operationId = "list", summary = "Servicio para listar los estudiantes", tags = { "Students" },
                        responses = { @ApiResponse(
                                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Student.class))),
                                responseCode = "200", 
                                description = "Sucess") }),
                produces = {MediaType.APPLICATION_JSON_VALUE}),
        @RouterOperation(path = "/api/v2/students/{id}", beanClass = StudentHandler.class, beanMethod = "listById", 
                operation = @Operation(operationId = "listById", summary = "Servicio para listar los estudiantes por id", tags = { "Students" },
                        parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "Id") }, 
                        responses = { @ApiResponse( content = @Content(schema = @Schema(implementation = Student.class)),
                                responseCode = "200", 
                                description = "Sucess") }),
                produces = {MediaType.APPLICATION_JSON_VALUE}),
        @RouterOperation(path = "/api/v2/students/save", beanClass = StudentHandler.class, beanMethod = "save", 
                operation = @Operation(operationId = "save", summary = "Servicio para registrar los estudiantes", tags = { "Students" },
                       requestBody = @RequestBody(required = true, description = "Request", content = @Content(schema = @Schema(implementation = Student.class))) ,
                        responses = { @ApiResponse( content = @Content(schema = @Schema(implementation = Student.class)),
                                responseCode = "200", 
                                description = "Sucess") }),
                produces = {MediaType.APPLICATION_JSON_VALUE}),
        @RouterOperation(path = "/api/v2/students/update", beanClass = StudentHandler.class, beanMethod = "update", 
                operation = @Operation(operationId = "update", summary = "Servicio para modificar los estudiantes", tags = { "Students" },
                       requestBody = @RequestBody(required = true, description = "Request", content = @Content(schema = @Schema(implementation = Student.class))) ,
                        responses = { @ApiResponse( content = @Content(schema = @Schema(implementation = Student.class)),
                                responseCode = "200", 
                                description = "Sucess") }),
                produces = {MediaType.APPLICATION_JSON_VALUE}),
        @RouterOperation(path = "/api/v2/students/delete/{id}", beanClass = StudentHandler.class, beanMethod = "delete", 
                operation = @Operation(operationId = "delete", summary = "Servicio para eliminar un estudiante", tags = { "Students" },
                        parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "Id") }, 
                        responses = { @ApiResponse( content = @Content(schema = @Schema(implementation = Void.class)),
                                responseCode = "200", 
                                description = "Sucess") }),
                produces = {MediaType.APPLICATION_JSON_VALUE})})
    @Bean
    public RouterFunction<ServerResponse> pathsStudents(StudentHandler handler) {
        return route(GET("/api/v2/students"), handler::list)
                .andRoute(GET("/api/v2/students/{id}"), handler::listById)
                .andRoute(POST("/api/v2/students/save"), handler::save)
                .andRoute(PUT("/api/v2/students/update"), handler::update)
                .andRoute(DELETE("/api/v2/students/delete/{id}"), handler::delete);
    }
}
