package fipp.silvio.vagasonlinebe.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public abstract class BaseMongoService {
    protected final MongoDatabase database;

    public BaseMongoService(MongoClient mongoClient) {
        this.database = mongoClient.getDatabase("vagas_online");
    }
}
