package fipp.silvio.vagasonlinebe.services;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import fipp.silvio.vagasonlinebe.entities.Cargo;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargosService extends BaseMongoService {

    private final Gson gson = new Gson();
    private static final String COLLECTION_NAME = "cargos";

    public CargosService(MongoClient mongoClient) {
        super(mongoClient);
    }

    public List<Cargo> getAll() {
        List<Cargo> cargoList = new ArrayList<>();
        try (MongoCursor<Document> cursor = database.getCollection(COLLECTION_NAME).find().iterator()) {
            while (cursor.hasNext())
                cargoList.add(gson.fromJson(cursor.next().toJson(), Cargo.class));
        } catch (Exception e) {
            System.out.println("Erro ao buscar cargos: " + e.getMessage());
        }
        return cargoList;
    }

    public Cargo getById(String id) {
        try {
            Document doc = database.getCollection(COLLECTION_NAME).find(Filters.eq("_id", new ObjectId(id))).first();
            return (doc != null) ? gson.fromJson(doc.toJson(), Cargo.class) : null;
        } catch (Exception e) {
            System.out.println("Erro ao buscar cargo por ID: " + e.getMessage());
            return null;
        }
    }

    public void create(Cargo cargo) {
        try {
            database.getCollection(COLLECTION_NAME).insertOne(Document.parse(gson.toJson(cargo)));
        } catch (Exception e) {
            System.out.println("Erro ao criar cargo: " + e.getMessage());
        }
    }

    public void update(String id, Cargo cargo) {
        try {
            Bson filtro = Filters.eq("_id", new ObjectId(id));
            Document novo = Document.parse(gson.toJson(cargo));
            novo.remove("_id");
            database.getCollection(COLLECTION_NAME).replaceOne(filtro, novo);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cargo: " + e.getMessage());
        }
    }

    public void delete(String id) {
        try {
            database.getCollection(COLLECTION_NAME).deleteOne(Filters.eq("_id", new ObjectId(id)));
        } catch (Exception e) {
            System.out.println("Erro ao deletar cargo: " + e.getMessage());
        }
    }
}
