package com.nt.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OExamResult {

	private Integer id;
	private Date dob;
	private Integer sem;
	private Double percentage;
	
}
