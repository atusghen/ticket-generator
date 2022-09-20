package com.unibg.ticketgeneratorVisualizer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TicketgeneratorVisualizerApplication {
//	private static List<JSONObject> nomiMense;
//	public  TicketgeneratorVisualizerApplication(){
//		nomiMense = new ArrayList<>();
//	}
	public static void main(String[] args) {
		SpringApplication.run(TicketgeneratorVisualizerApplication.class, args);
		while(true)
		{
//			Utilità.visualizzazione();
			Utilità.visualizzazione2();

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder)
//	{
//		return builder.build();
//	}
//
//	@Bean
//	public CommandLineRunner run(RestTemplate restTemplate)
//	{
//		return args ->{
//			TipoA[] r = restTemplate.getForObject(
//					"http://localhost:8080/allStack",
//					TipoA[].class);
//		//Arrays.stream(r).forEach(System.out::println);
//		};
//	}


}
