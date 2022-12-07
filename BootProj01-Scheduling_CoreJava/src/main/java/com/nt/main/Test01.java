package com.nt.main;

import java.util.Date;
import java.util.Timer;

import com.nt.task.Task1;

public class Test01 {
	public static void main(String[] args) {
		System.out.println("main start :"+new Date());
		Timer timer=new Timer();
		timer.schedule(new Task1(),2000,3000);//period of time
//		timer.schedule(new Task1(), new Date(122,10,11,9,34,00));//point of time
		
		System.out.println("main end :"+new Date());
	}
}
