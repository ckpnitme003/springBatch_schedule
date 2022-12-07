package com.nt.config;

import java.io.IOException;
import java.io.Writer;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

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

	/*@Bean(name="writer")
	public FlatFileItemWriter<OExamResult> createWriter(){
		System.out.println("Flatfile Writer ===================");
		FlatFileItemWriter<OExamResult> writer=new FlatFileItemWriter<OExamResult>();
		
		 writer.setResource(new FileSystemResource("E://store/batch/TopperResult.csv"));
		writer.setAppendAllowed(true);
		//==============
		writer.setHeaderCallback(new FlatFileHeaderCallback() {
		    @Override
		    public void writeHeader(Writer writer) throws IOException {
		       
		        writer.write("id dob sem percentage");
		    }
		});
		//==============
		writer.setLineAggregator(getDelimitedLineAggregator());
		return writer;
	}
	//set name and delimitor
	private DelimitedLineAggregator<OExamResult> getDelimitedLineAggregator() {
	    BeanWrapperFieldExtractor<OExamResult> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<OExamResult>();
	    beanWrapperFieldExtractor.setNames(new String[]{"id", "dob", "sem", "percentage"});
	
	    DelimitedLineAggregator<OExamResult> aggregator = new DelimitedLineAggregator<OExamResult>();
	    aggregator.setDelimiter(",");
	    aggregator.setFieldExtractor(beanWrapperFieldExtractor);
	    return aggregator;
	
	}*/
	@Bean(name="writer")
	public FlatFileItemWriter<OExamResult> createWriter(){
		System.out.println("Flatfile Writer ===================");
		
		return new FlatFileItemWriterBuilder<OExamResult>()
				.name("file-writer")
				.resource(new FileSystemResource("E://store/batch/ToppersJPA.csv"))
				.lineSeparator("\r\n")
				.delimited().delimiter(",")
				.names("id", "dob", "sem", "percentage")
				.build();
	}

	@Bean(name = "Reader")
	public JpaPagingItemReader<IExamResult> createReader() {
		System.out.println("Jpa item Reader===================");

		return new JpaPagingItemReaderBuilder<IExamResult>()
				.name("jpa-reader")
				.entityManagerFactory(emFactory)
				.queryString("select e from IExamResult e")
				.build();
	}

	@Bean(name = "step1")
	public Step createStep1() {
		return sbFactory.get("step1").<IExamResult, OExamResult>chunk(5).reader(createReader()).processor(processor)
				.writer(createWriter()).build();

	}

	@Bean(name = "job1")
	public Job createJob1() {
		return jbFactory.get("job1").incrementer(new RunIdIncrementer()).listener(listener).start(createStep1())
				.build();
	}
}
