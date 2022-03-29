package com.atomic.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The Class StudentDTO.
 *
 * @author A. Juarez
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDTO implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The student id. */
	private Integer studentId;

	/** The name. */
	private String name;

	/** The surnames. */
	private String surnames;

	/** The curp. */
	private String curp;

	/** The age. */
	private Integer age;
}
