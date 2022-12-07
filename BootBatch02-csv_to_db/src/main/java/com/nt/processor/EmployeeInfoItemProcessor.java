package com.nt.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.nt.model.Employee;

@Component("empProcessor")
public class EmployeeInfoItemProcessor implements ItemProcessor<Employee,Employee> {

	@Override
	public Employee process(Employee item) throws Exception {
		if(item.getSal()>=50000) {
			item.setGrossSal(item.getSal()*1.4);
			item.setNetSal(item.getGrossSal()*0.8);
			return item;
		}else 
			return null;
	}
}
