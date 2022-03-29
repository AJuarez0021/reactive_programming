package com.atomic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * The Class Courses.
 *
 * @author ajuarez
 */
@Table
@Data
@ApiModel(value = "Courses")
public class Courses {

    /** The courses id. */
    @Id
    @Column("courses_id")
    @ApiModelProperty(notes = "coursesId")
    private Integer coursesId;

    /** The name. */
    @Column("name")
    @ApiModelProperty(notes = "name - The length must be between 3 and 20 characters")
    @Size(min = 3, max = 20)
    @NotEmpty(message = "The name field cannot be empty")
    private String name;

    /** The acronym. */
    @Column("acronym")
    @ApiModelProperty(notes = "acronym - The length must be between 3 and 5 characters")
    @Size(min = 3, max = 5)
    @NotEmpty(message = "The acronym field cannot be empty")
    private String acronym;

    /** The status. */
    @Column("status")
    @ApiModelProperty(notes = "status")
    @NotNull(message = "The status field cannot be empty")
    private boolean status;

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
