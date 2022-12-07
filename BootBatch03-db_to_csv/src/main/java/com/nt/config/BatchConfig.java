package com.nt.config;

import java.io.IOException;
import java.io.Writer;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

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
	private DataSource ds;
	
	
	@Bean(name="reader")
	public JdbcCursorItemReader<ExamResult> createReader(){
		
		System.out.println("jdbccursor reader ===================");
		JdbcCursorItemReader<ExamResult> reader=new JdbcCursorItemReader<>();
		
		reader.setDataSource(ds);
		reader.setSql("select id,dob,sem,percentage from Exam_Result");
		
		/*RowMapper<Employee> rowMapper=new EmployeeRowMapper();
		reader.setRowMapper(rowMapper);*/
		reader.setRowMapper( (rs,rowNum)-> new ExamResult(rs.getInt(1),
				rs.getDate(2),
				rs.getInt(3),
				rs.getDouble(4))
		
				);
		return reader;
	}
	@Bean(name="writer")
	public FlatFileItemWriter<ExamResult> createWriter(){
		System.out.println("Flatfile Writer ===================");
		FlatFileItemWriter<ExamResult> writer=new FlatFileItemWriter<ExamResult>();
		
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
	private DelimitedLineAggregator<ExamResult> getDelimitedLineAggregator() {
        BeanWrapperFieldExtractor<ExamResult> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<ExamResult>();
        beanWrapperFieldExtractor.setNames(new String[]{"id", "dob", "sem", "percentage"});

        DelimitedLineAggregator<ExamResult> aggregator = new DelimitedLineAggregator<ExamResult>();
        aggregator.setDelimiter(",");
        aggregator.setFieldExtractor(beanWrapperFieldExtractor);
        return aggregator;

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
