package com.nt.listener;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;
@Component("jeListener")
public class EmpMgmtListener implements JobExecutionListener {

	private long start,end;
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("start App"+new Date());
		start=System.currentTimeMillis();
		System.out.println("status"+jobExecution.getStatus());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("end App"+new Date());
		end=System.currentTimeMillis();
		System.out.println("status"+jobExecution.getStatus());
		System.out.println("Time taken for Execution :"+(end-start)+" millies");

	}

}
