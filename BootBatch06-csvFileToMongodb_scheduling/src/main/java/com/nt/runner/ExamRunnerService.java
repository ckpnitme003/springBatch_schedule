package com.nt.runner;

import java.util.Random;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExamRunnerService {

	@Autowired
	private JobLauncher launcher;
	@Autowired
	private Job job;
	
	@Scheduled(cron = "0 37/1 9 * * *")
	public void run() throws Exception {

		//optional
		JobParameters parameter=new JobParametersBuilder().addLong("eid",new Random().nextLong()).toJobParameters();
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
