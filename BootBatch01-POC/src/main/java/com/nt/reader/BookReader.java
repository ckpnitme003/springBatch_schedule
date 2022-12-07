package com.nt.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component("biReader")
public class BookReader implements ItemReader<String> {
	
	private String[] booksInfo= {"cj","Adv java","oracle","spring","data Structure"};
	int count=0;
	public BookReader() {
		System.out.println("BookReader.BookReader()");
	}
	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("read()");
		if(count<booksInfo.length)
			return booksInfo[count++];
		else
			return null;
	}

}
