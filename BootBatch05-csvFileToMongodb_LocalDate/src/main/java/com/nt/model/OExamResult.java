package com.nt.model;



import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OExamResult {

	@Id
	private Integer id;
	private LocalDate dob;
	private Integer sem;
	private Double percentage;
	
}
