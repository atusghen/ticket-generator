package com.unibg.ticketgeneratorClient;

import com.unibg.ticketgeneratorClient.gui.Utilità;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketgeneratorClientApplication {

	public static void main(String[] args) {

		SpringApplication.run(TicketgeneratorClientApplication.class, args);
		while(true)
		{
			Utilità.interfaccia();
			Utilità.invioRichiesta();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

	}

}
