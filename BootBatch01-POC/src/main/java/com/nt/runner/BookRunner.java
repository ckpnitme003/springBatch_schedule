package com.nt.runner;

import java.util.Random;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component("runner")
public class BookRunner implements CommandLineRunner {

	@Autowired
	private JobLauncher launcher;
	@Autowired
	private Job job;
	@Override
	public void run(String... args) throws Exception {

		//optional
		JobParameters parameter=new JobParametersBuilder().addLong("id",new Random().nextLong()).toJobParameters();
		try {
		JobExecution execution=launcher.run(job, parameter);
		
		System.out.println("exit status :"+execution.getExitStatus());;
		System.out.println("start time :"+execution.getStartTime());;
		System.out.println("end time :"+execution.getEndTime());;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
