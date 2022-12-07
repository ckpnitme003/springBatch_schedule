package com.nt.processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.nt.model.IExamResult;
import com.nt.model.OExamResult;

@Component("examProcessor")
public class ExamResultInfoItemProcessor implements ItemProcessor<IExamResult,OExamResult> {

	@Override
	public OExamResult process(IExamResult item) throws Exception {
		System.out.println("processor=================");
		if(item.getPercentage()>=96) {
			OExamResult result=new OExamResult();
			result.setId(item.getId());
			result.setDob(LocalDate.parse(item.getDob(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			result.setSem(item.getSem());
			result.setPercentage(item.getPercentage());
			
			return result;
		}
		else
			return null;
	}
}
