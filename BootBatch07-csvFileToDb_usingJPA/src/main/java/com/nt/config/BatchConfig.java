package com.nt.config;



import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.nt.listener.EmpMgmtListener;
import com.nt.model.IExamResult;
import com.nt.model.OExamResult;
import com.nt.processor.ExamResultInfoItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jbFactory;
	@Autowired
	private StepBuilderFactory sbFactory;
	@Autowired
	private EmpMgmtListener listener;
	@Autowired
	private ExamResultInfoItemProcessor processor;
	
	@Autowired
	private EntityManagerFactory emFactory;
	
	
	@Bean(name="reader")
	public FlatFileItemReader<IExamResult> createReader(){
		
		System.out.println("Flatfile Item reader ===================");
		return new FlatFileItemReaderBuilder<IExamResult>()
				.name("ff-reader")
				.resource(new ClassPathResource("TopperResult.csv"))
				.delimited().delimiter(",")
				.names("id","dob","sem","percentage")
				.targetType(IExamResult.class)
				.build();
				
	}
	@Bean(name="writer")
	public JpaItemWriter<OExamResult> createWriter(){
		System.out.println("Mongo Item Writer ===================");
		
		return new JpaItemWriterBuilder<OExamResult>()
				.entityManagerFactory(emFactory)
				.build();
	}
	
	@Bean(name="step1")
	public Step createStep1() {
		return sbFactory.get("step1")
				.<IExamResult,OExamResult>chunk(5)
				.reader(createReader())
				.processor(processor)
				.writer(createWriter())
				.build();
		
	}
	@Bean(name="job1")
	public Job createJob1() {
		return jbFactory.get("job1")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(createStep1())
				.build();
	}
}
