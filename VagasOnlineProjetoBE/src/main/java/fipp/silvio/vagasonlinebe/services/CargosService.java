package fipp.silvio.vagasonlinebe.services;

import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import fipp.silvio.vagasonlinebe.entities.Cargo;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CargosService {

    private final String connectionString = "mongodb://localhost:27017";
    private final String dbName = "vagas_online";
    private final String collectionName = "cargos";

    public List<Cargo> getAll() {
        List<Cargo> cargoList = new ArrayList<>();

        try (MongoClient mongoClient = MongoClients.create(connectionString))
        {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            MongoCursor<Document> cursor = collection.find().iterator();

            while (cursor.hasNext())
                cargoList.add(new Gson().fromJson(cursor.next().toJson(), Cargo.class));

        } catch (Exception e) {
            System.out.println("Erro ao buscar cargos: " + e.getMessage());
        }

        return cargoList;
    }

    public Cargo getById(String id) {
        Cargo cargo = null;

        try (MongoClient mongoClient = MongoClients.create(connectionString))
        {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document doc = collection.find(Filters.eq("_id", new ObjectId(id))).first();
            if (doc != null)
                cargo = new Gson().fromJson(doc.toJson(), Cargo.class);

        } catch (Exception e) {
            System.out.println("Erro ao buscar  cargo por ID: " + e.getMessage());
        }

        return cargo;
    }

    public void create(Cargo cargo) {
        try (MongoClient mongoClient = MongoClients.create(connectionString))
        {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document doc = Document.parse(new Gson().toJson(cargo));
            collection.insertOne(doc);
        } catch (Exception e) {
            System.out.println("Erro ao criar cargo: " + e.getMessage());
        }
    }

    public void update(String id, Cargo cargo) {
        try (MongoClient mongoClient = MongoClients.create(connectionString))
        {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Bson filtro = Filters.eq("_id", new ObjectId(id));
            Document novo = Document.parse(new Gson().toJson(cargo));
            collection.replaceOne(filtro, novo);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cargo: " + e.getMessage());
        }
    }

    public void delete(String id) {
        try (MongoClient mongoClient = MongoClients.create(connectionString))
        {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
        } catch (Exception e) {
            System.out.println("Erro ao deletar cargo: " + e.getMessage());
        }
    }
}
