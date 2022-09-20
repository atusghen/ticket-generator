package com.unibg.ticketgeneratorClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketgeneratorOperatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketgeneratorOperatorApplication.class, args);
		Operatore.serviSuccessivo();
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
//			TipoA r = restTemplate.getForObject(
//					"http://localhost:8080/tipoA",
//					TipoA.class);
//			System.out.println(r.getPre()+ ((r.getId() >10) ? "0" : "00")+ r.getId());
//		};
//	}

}
