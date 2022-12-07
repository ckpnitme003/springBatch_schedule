package com.nt.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.nt.listener.EmpMgmtListener;
import com.nt.model.Employee;
import com.nt.processor.EmployeeInfoItemProcessor;

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
	private EmployeeInfoItemProcessor processor;
	@Autowired
	private DataSource ds;
	
	//approch -1 ========================================
	/*@Bean(name="reader")
	public FlatFileItemReader<Employee> createReader(){
		
		FlatFileItemReader<Employee> reader=new FlatFileItemReader<Employee>();
		
		//set csv file path
		reader.setResource(new ClassPathResource("EmployeeInfo.csv"));
		
		//create line Mapper(to get line)
		DefaultLineMapper<Employee> lineMapper=new DefaultLineMapper<Employee>();
	
		//create delimiter obj(to get tokens)
		DelimitedLineTokenizer tokenizer=new DelimitedLineTokenizer();
		tokenizer.setDelimiter(",");
		tokenizer.setNames("eid","ename","eadd","sal");
		
		//create Field set Mapper(to set tokens to model class)
		BeanWrapperFieldSetMapper<Employee> fieldMapper=new BeanWrapperFieldSetMapper<Employee>();
		fieldMapper.setTargetType(Employee.class);
		
		//set tokenizer,fieldSet mapper to Line Mapper
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(fieldMapper);
		
		//set line Mapper to reader obj
		reader.setLineMapper(lineMapper);
		
		return reader;
	}
	@Bean(name="writer")
	public JdbcBatchItemWriter<Employee> createWriter(){
		JdbcBatchItemWriter<Employee> writer=new JdbcBatchItemWriter<Employee>();
		
		writer.setDataSource(ds);
		writer.setSql("Insert into batch_EmployeeInfo values(:eid,:ename,:eadd,:sal,:grossSal,:netSal)");
		
		BeanPropertyItemSqlParameterSourceProvider<Employee> provider=new BeanPropertyItemSqlParameterSourceProvider<Employee>();
		
		writer.setItemSqlParameterSourceProvider(provider);
		
		return writer;
	}*/
	
	/*//approch -2 ====================================
	@Bean(name="reader")
	public FlatFileItemReader<Employee> createReader(){
		
		FlatFileItemReader<Employee> reader=new FlatFileItemReader<Employee>();
		
		//set csv file path
		reader.setResource(new ClassPathResource("EmployeeInfo.csv"));
		reader.setLineMapper(new DefaultLineMapper<Employee>() {
			{
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {
					{
						setTargetType(Employee.class);
					}
				});
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setDelimiter(",");
						setNames("eid","ename","eadd","sal");
					}
				});
			}
		});
		return reader;
	}
	@Bean(name="writer")
	public JdbcBatchItemWriter<Employee> createWriter(){
		JdbcBatchItemWriter<Employee> writer=new JdbcBatchItemWriter<Employee>() {
			{
				setDataSource(ds);
				setSql("Insert into batch_EmployeeInfo values(:eid,:ename,:eadd,:sal,:grossSal,:netSal)");
				setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Employee>());
			}
		};
		return writer;
	}*/
	//approch -3============================================
	@Bean(name="reader")
	public FlatFileItemReader<Employee> createReader(){
		
		
		return new FlatFileItemReaderBuilder()
				.name("Flat-reader")
				.resource(new ClassPathResource("EmployeeInfo.csv"))
				.delimited()
				.names("eid","ename","eadd","sal")
				.targetType(Employee.class)
				.build();
	}
	@Bean(name="writer")
	public JdbcBatchItemWriter<Employee> createWriter(){
		
		return new JdbcBatchItemWriterBuilder()
				.dataSource(ds)
				.sql("Insert into batch_EmployeeInfo values(:eid,:ename,:eadd,:sal,:grossSal,:netSal)")
				.beanMapped()
				.build();
	}
	@Bean(name="step1")
	public Step createStep1() {
		return sbFactory.get("step1")
				.<Employee,Employee>chunk(5)
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
