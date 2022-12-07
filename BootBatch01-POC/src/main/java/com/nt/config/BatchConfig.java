package com.nt.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nt.listener.BookMgmtListener;
import com.nt.processor.BookProcessor;
import com.nt.reader.BookReader;
import com.nt.writer.BookWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private JobBuilderFactory jbFactory;
	@Autowired
	private StepBuilderFactory sbFactory;
	
	@Autowired
	private BookReader reader;
	
	@Autowired
	private BookProcessor processor;
	
	@Autowired
	private BookWriter writer;
	
	@Autowired
	private BookMgmtListener listener;
	
	//create step Obj
	@Bean(name = "step1")
	public Step createStep1() {
		return sbFactory.get("step1")
				.<String,String>chunk(3)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	//create Job Obj
	@Bean(name="job1")
	public Job createJob1() {
		return jbFactory.get("job1")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(createStep1())
				.build();
	}
}
