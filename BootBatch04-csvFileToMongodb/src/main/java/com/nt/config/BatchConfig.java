package com.nt.config;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.nt.listener.EmpMgmtListener;
import com.nt.model.ExamResult;
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
	private MongoTemplate template;
	
	
	@Bean(name="reader")
	public FlatFileItemReader<ExamResult> createReader(){
		
		System.out.println("Flatfile Item reader ===================");
		return new FlatFileItemReaderBuilder<ExamResult>()
				.name("ff-reader")
				.resource(new ClassPathResource("TopperResult.csv"))
				.delimited().delimiter(",")
				.names("id","dob","sem","percentage")
				.targetType(ExamResult.class)
				.build();
				
	}
	@Bean(name="writer")
	public MongoItemWriter<ExamResult> createWriter(){
		System.out.println("Mongo Item Writer ===================");
		
		return new MongoItemWriterBuilder<ExamResult>()
				.collection("topBrains")
				.template(template)
				.build();
	}
	
	@Bean(name="step1")
	public Step createStep1() {
		return sbFactory.get("step1")
				.<ExamResult,ExamResult>chunk(5)
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
