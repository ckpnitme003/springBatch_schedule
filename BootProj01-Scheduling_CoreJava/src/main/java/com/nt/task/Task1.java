package com.nt.task;

import java.util.Date;
import java.util.TimerTask;

public class Task1 extends TimerTask {

	@Override
	public void run() {
			System.out.println("Task1 :"+new Date());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m1();
	}
	public void m1() {
		System.out.println("m1 method");
	}
	

}
