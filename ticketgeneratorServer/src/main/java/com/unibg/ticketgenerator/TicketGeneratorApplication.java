package com.unibg.ticketgenerator;

import com.unibg.ticketgenerator.srv.library.OPEesecutore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableMongoRepositories(basePackages = "com.unibg.ticketgenerator")
@ComponentScan(basePackages = {"com.unibg.ticketgenerator"})
public class TicketGeneratorApplication {

	@Bean(name = "opeExecutor")
	public OPEesecutore opeExecutor() {
		OPEesecutore opeExecutor = new OPEesecutore();
		return opeExecutor;
	}


//	public static MongoCollection<Document> connectToMongoCollection() {
//
////		String uri="mongodb+srv://atusghen:3b5UEli9hv0hZOaHJGzH@cluster0.7kcuiyx.mongodb.net/?retryWrites=true&w=majority";
//		try (MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder()
//				.uuidRepresentation(UuidRepresentation.JAVA_LEGACY)
//				.build())) {
//			MongoDatabase database = mongoClient.getDatabase("Ticket");
//
//			try {
//				Bson command = new BsonDocument("ping", new BsonInt64(1));
//				Document commandResult = database.runCommand(command);
//				System.out.println("Connected successfully to server.");
//			} catch (MongoException me) {
//				System.err.println("An error occurred while attempting to run a command: " + me);
//			}
//			MongoCollection<Document> collection = database.getCollection("DBCampusCollection");
//			return collection;
//		}
//
//	}

public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(TicketGeneratorApplication.class, args);
		ctx.close();
	}
}
