package com.nt.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("biProcessor")
public class BookProcessor implements ItemProcessor<String, String> {

	public BookProcessor() {
		System.out.println("BookProcessor.BookProcessor()");
	}
	@Override
	public String process(String item) throws Exception {
		System.out.println("process()");
		String title=null;
		if(item.equalsIgnoreCase("cj"))
			title="cj by HK";
		else if(item.equalsIgnoreCase("Adv java"))
			title="Adv java by Nataraj";
		else if(item.equalsIgnoreCase("oracle"))
			title="oracle by sudhakar";
		else if(item.equalsIgnoreCase("spring"))
			title="spring by Nataraj";
		else if(item.equalsIgnoreCase("data Structure"))
			title="data Structure by prakash";
		
		return title;
	}

}
