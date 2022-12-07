package com.nt.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.nt.model.ExamResult;

@Component("examProcessor")
public class ExamResultInfoItemProcessor implements ItemProcessor<ExamResult,ExamResult> {

	@Override
	public ExamResult process(ExamResult item) throws Exception {
		System.out.println("processor=================");
		if(item.getPercentage()>=96)
			return item;
		else
			return null;
	}
}
