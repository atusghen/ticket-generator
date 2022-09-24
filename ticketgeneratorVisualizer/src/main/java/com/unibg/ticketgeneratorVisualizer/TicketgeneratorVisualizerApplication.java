package com.unibg.ticketgeneratorVisualizer;

import com.unibg.ticketgeneratorVisualizer.gui.Utilità;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketgeneratorVisualizerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TicketgeneratorVisualizerApplication.class, args);
		while(true)
		{
			Utilità.visualizzazione2();

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
