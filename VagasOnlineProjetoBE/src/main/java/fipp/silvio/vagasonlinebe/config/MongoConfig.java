package fipp.silvio.vagasonlinebe.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(CONNECTION_STRING);
    }
}
