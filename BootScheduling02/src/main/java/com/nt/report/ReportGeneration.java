package com.nt.report;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportGeneration {
	
	/*@Scheduled(initialDelay = 2000,fixedDelay = 3000)
	public void m1() {
		System.out.println("start : "+new Date());
		System.out.println("end : "+new Date());
	}*/
	/*@Scheduled(initialDelay = 2000)//Exception
	public void m1() {
		System.out.println("start : "+new Date());
		System.out.println("end : "+new Date());
	}*/
	/*@Scheduled(fixedDelay = 2000)
	public void m1() {
		System.out.println("start : "+new Date());
		System.out.println("end : "+new Date());
	}*/
	//fixedDelay=======================
	//case-1==========
	/*@Scheduled(fixedDelay = 2000)
	public void m1() {
		System.out.println("start : "+new Date());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end : "+new Date());
	}*/
	/*//case-2==========
	@Scheduled(fixedDelay = 3000)
	public void m1() {
		System.out.println("start : "+new Date());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end : "+new Date());
	}*/
	//fixedRate===================
	/*	//case-1==========
		@Scheduled(fixedRate =5000)
		public void m1() {
			System.out.println("start : "+new Date());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("end : "+new Date());
		}*/
	//case-2==========
	/*@Scheduled(fixedRate =3000)
	public void m1() {
		System.out.println("start : "+new Date());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end : "+new Date());
	}*/
	
	//taking multiple scheduling method
	/*@Scheduled(initialDelay = 2000,fixedDelay = 3000)
	public void m1() {
		System.out.println("m1 start : "+new Date());
		System.out.println("m1 end : "+new Date());
		System.out.println("Thread name :"+Thread.currentThread().getName());
	}
	@Scheduled(initialDelay = 2000,fixedRate= 3000)
	public void m2() {
		System.out.println("m2 start : "+new Date());
		System.out.println("m2 end : "+new Date());
		System.out.println("Thread name :"+Thread.currentThread().getName());
	}*/
	
	//cron example========================
	/*//1)
	@Scheduled(cron = "* * * * * *")//every sec,every minute,every hour..
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	/*//2)
	@Scheduled(cron = "10 * * * * *")//10 sec of every minute
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	/*//3)
	@Scheduled(cron = "10 8 * * * *")//10 sec,8 minute of every hour
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	//4)
	/*@Scheduled(cron = "00 56 10 * * *")//00 sec,56 minute,10hour  of every day
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	/*//5)
	@Scheduled(cron = "10 * 10 * * *")//10 sec,every minute ,10hour  of every day
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	/*//6)
	@Scheduled(cron = "* 58 10 * * *")//every sec,58 minute ,10hour  of every day
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	/*//7)
	@Scheduled(cron = "0 1 10,11 * * *")//0 sec,1 min,10hour&11 hour  of every day
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	/*//8)
	@Scheduled(cron = "0 2 11-14 * * *")//0 sec,2 min,11AMto 2PM  of every day
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	/*//9)
	@Scheduled(cron = "0 0 11 15 * *")//0 sec,0 min,11AM ,15th day/date of every month
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	/*//10)
	@Scheduled(cron = "0 0 11 15 11 *")//0 sec,0 min,11AM ,15th day/date,11 month of every year
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	/*//11)
	@Scheduled(cron = "0 0 11 15 11 TUE")//0 sec,0 min,11AM ,15th day/date,11 month ,tuesday
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	
	//========period of time
	/*//1)
	@Scheduled(cron = "0/10 * * * * *")//0 sec, 10sec gap
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	/*//2)
	@Scheduled(cron = "10 5/1 * * * *")//10 sec,5 min start, gap=1min
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	/*//3)
	@Scheduled(cron = "30 11/1 10 * * *")//30 sec ,11min start, gap=1min at every day 10 AM to 10:59:59
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	/*//4)
	@Scheduled(cron = "30/10 24/1 11 * * *")//30 sec,10 sec gap ,24 min start, gap=1min at every day 11 AM to 11:59:59
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	/*//5)
	@Scheduled(cron = "0 31 11 * * wed")//11:31:00 execute every wed
	public void m1() {
		System.out.println("cron m1 method :"+new Date());
	}*/
	
}
