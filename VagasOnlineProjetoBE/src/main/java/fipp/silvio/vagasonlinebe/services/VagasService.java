package fipp.silvio.vagasonlinebe.services;

import com.google.gson.Gson;
import com.mongodb.client.*;
import fipp.silvio.vagasonlinebe.entities.Vaga;

import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VagasService {
    public List<Vaga> getAll(){
        List<Vaga> vagaList = new ArrayList<>();
        try {
            String connectionString = "mongodb://localhost:27017";
            MongoClient mongoClient = MongoClients.create(connectionString);
            //Accessando um database (base de dados)
            MongoDatabase database = mongoClient.getDatabase("vagas_online");

            MongoCollection<Document> collection = database.getCollection("vagas");
            MongoCursor<Document> cursor = collection.find().iterator();
            // insere os Documents em um ArrayList de Carro

            while (cursor.hasNext())
                vagaList.add(new Gson().fromJson(cursor.next().toJson(), Vaga.class));
        }
        catch (Exception e){
            System.out.println(e);
        }
        return vagaList;
    }
}
