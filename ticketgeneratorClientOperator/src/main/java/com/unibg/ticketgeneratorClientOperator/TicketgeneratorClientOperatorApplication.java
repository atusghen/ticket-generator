package com.unibg.ticketgeneratorClientOperator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class TicketgeneratorClientOperatorApplication {

	private static String token;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TicketgeneratorClientOperatorApplication.class, args);

		token = String.valueOf(Operatore.login().getO().getJwt());

		System.out.println(token);

		while(true) {

			Operatore.interfaccia();
			Operatore.serviSuccessivo();
			Operatore.aggiungiUtente();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
