package com.nt.model;



import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Jpa_Exam_Result")
public class OExamResult {

	@Id
	private Integer id;
	private LocalDate dob;
	private Integer sem;
	private Double percentage;
	
}
