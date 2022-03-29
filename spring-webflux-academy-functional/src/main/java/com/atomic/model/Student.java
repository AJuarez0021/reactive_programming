package com.atomic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * The Class Student.
 *
 * @author ajuarez
 */
@Table
@Data
public class Student {

    /** The student id. */
    @Id
    @Column("student_id")    
    private Integer studentId;

    /** The name. */
    @Column("names")    
    @Size(min = 3, max = 20)
    @NotEmpty(message = "The name field cannot be empty")
    private String name;

    /** The surnames. */
    @Column("surnames")    
    @Size(min = 3, max = 50)
    @NotEmpty(message = "The surnames field cannot be empty")
    private String surnames;

    /** The curp. */
    @Column("curp")    
    @NotEmpty(message = "The curp field cannot be empty")
    private String curp;

    /** The age. */
    @Column("age")
    @NotNull(message = "The age field cannot be empty")
    private Integer age;

    /** The creation datetime. */
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column("creation_datetime")
    @CreatedDate
    @JsonIgnore
    private LocalDateTime creationDatetime;

    /** The modification datetime. */
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column("modification_datetime")
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime modificationDatetime;

}
