package fipp.silvio.vagasonlinebe.services;

import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import fipp.silvio.vagasonlinebe.entities.Empresa;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresasService {

    private final String connectionString = "mongodb://localhost:27017";
    private final String dbName = "vagas_online";
    private final String collectionName = "empresas";

    public List<Empresa> getAll() {
        List<Empresa> empresaList = new ArrayList<>();

        try (MongoClient mongoClient = MongoClients.create(connectionString))
        {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            MongoCursor<Document> cursor = collection.find().iterator();

            while (cursor.hasNext())
                empresaList.add(new Gson().fromJson(cursor.next().toJson(), Empresa.class));

        } catch (Exception e) {
            System.out.println("Erro ao buscar empresas: " + e.getMessage());
        }

        return empresaList;
    }

    public Empresa getById(String id) {
        Empresa empresa = null;

        try (MongoClient mongoClient = MongoClients.create(connectionString))
        {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document doc = collection.find(Filters.eq("_id", new ObjectId(id))).first();
            if (doc != null)
                empresa = new Gson().fromJson(doc.toJson(), Empresa.class);

        } catch (Exception e) {
            System.out.println("Erro ao buscar empresa por ID: " + e.getMessage());
        }

        return empresa;
    }

    public void create(Empresa empresa) {
        try (MongoClient mongoClient = MongoClients.create(connectionString))
        {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Document doc = Document.parse(new Gson().toJson(empresa));
            collection.insertOne(doc);
        } catch (Exception e) {
            System.out.println("Erro ao criar empresa: " + e.getMessage());
        }
    }

    public void update(String id, Empresa empresa) {
        try (MongoClient mongoClient = MongoClients.create(connectionString))
        {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            Bson filtro = Filters.eq("_id", new ObjectId(id));
            Document novo = Document.parse(new Gson().toJson(empresa));
            collection.replaceOne(filtro, novo);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar empresa: " + e.getMessage());
        }
    }

    public void delete(String id) {
        try (MongoClient mongoClient = MongoClients.create(connectionString))
        {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
        } catch (Exception e) {
            System.out.println("Erro ao deletar empresa: " + e.getMessage());
        }
    }
}
