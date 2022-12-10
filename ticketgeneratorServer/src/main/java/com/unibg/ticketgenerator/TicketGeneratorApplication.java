package com.unibg.ticketgenerator;

import com.unibg.ticketgenerator.srv.library.OPEesecutore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.unibg.ticketgenerator")
public class TicketGeneratorApplication {

	@Bean(name = "opeExecutor")
	public OPEesecutore opeExecutor() {
		OPEesecutore opeExecutor = new OPEesecutore();
		return opeExecutor;
	}

	public static void main(String[] args) {
		SpringApplication.run(TicketGeneratorApplication.class, args);

	}
}
