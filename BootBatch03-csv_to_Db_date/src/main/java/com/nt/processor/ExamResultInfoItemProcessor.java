package com.nt.processor;

import java.text.SimpleDateFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.nt.model.IExamResult;
import com.nt.model.OExamResult;

@Component("examProcessor")
public class ExamResultInfoItemProcessor implements ItemProcessor<IExamResult,OExamResult> {

	@Override
	public OExamResult process(IExamResult item) throws Exception {
		System.out.println("processor=================");
		if(item.getPercentage()>=95.0) {
			OExamResult result=new OExamResult();
			result.setId(item.getId());
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			result.setDob(sdf.parse(item.getDob()));
			result.setSem(item.getSem());
			result.setPercentage(item.getPercentage());
			
			return result;
		}
		else
			return null;
	}
}
