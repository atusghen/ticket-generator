package com.unibg.ticketgeneratorVisualizer;

import com.unibg.ticketgeneratorVisualizer.gui.Interfaccia;
import com.unibg.ticketgeneratorVisualizer.gui.Utilità;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketgeneratorVisualizerApplication {
	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(TicketgeneratorVisualizerApplication.class, args);
		Interfaccia ui = new Interfaccia();
		while(true)
		{

			Utilità.visualizzazione2(ui);


			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
