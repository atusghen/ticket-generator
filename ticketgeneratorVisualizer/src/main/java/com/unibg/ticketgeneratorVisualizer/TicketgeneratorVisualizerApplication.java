package com.unibg.ticketgeneratorVisualizer;

import com.unibg.ticketgeneratorVisualizer.gui.Interfaccia;
import com.unibg.ticketgeneratorVisualizer.gui.Utilità;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketgeneratorVisualizerApplication {
	private static String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXN1YWxpenphdG9yZSIsImlhdCI6MTY3MjQwOTEyMSwiZXhwIjoxNjcyNDA5MTIxfQ.Qq4ZUYDUURnljEoaZeXycNb7wZX9UO2SxmjX7JvU9t3zL7If3bMZ00bVErIX1crGuLqx6wK0srrwJu44xI4bog";
	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(TicketgeneratorVisualizerApplication.class, args);
		Interfaccia ui = new Interfaccia();
		//InterfaceGUI i = new InterfaceGUI();
		while(true)
		{

			Utilità.visualizzazione2(ui, token);


			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
