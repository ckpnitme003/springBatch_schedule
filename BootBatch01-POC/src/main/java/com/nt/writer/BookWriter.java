package com.nt.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component("biWriter")
public class BookWriter implements ItemWriter<String> {

	public BookWriter() {
		System.out.println("BookWriter.BookWriter()");
	}
	@Override
	public void write(List<? extends String> items) throws Exception {
		System.out.println("write()");
		System.out.println(items);
	}

}
