package com.atomic.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The Class CoursesDTO.
 *
 * @author A. Juarez
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoursesDTO implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The courses id. */
	private Integer coursesId;

	/** The name. */
	private String name;

	/** The acronym. */
	private String acronym;

	/** The status. */
	private boolean status;

}
