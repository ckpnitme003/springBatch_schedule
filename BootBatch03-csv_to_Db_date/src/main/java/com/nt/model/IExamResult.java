package com.nt.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IExamResult {

	private Integer id;
	private String dob;
	private Integer sem;
	private Double percentage;
	
}
