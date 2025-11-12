package fipp.silvio.vagasonlinebe.services;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import fipp.silvio.vagasonlinebe.entities.Empresa;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresasService extends BaseMongoService {

    private final Gson gson = new Gson();
    private static final String COLLECTION_NAME = "empresas";

    public EmpresasService(MongoClient mongoClient) {
        super(mongoClient);
    }

    public List<Empresa> getAll() {
        List<Empresa> empresaList = new ArrayList<>();
        try (MongoCursor<Document> cursor = database.getCollection(COLLECTION_NAME).find().iterator()) {
            while (cursor.hasNext())
                empresaList.add(gson.fromJson(cursor.next().toJson(), Empresa.class));
        } catch (Exception e) {
            System.out.println("Erro ao buscar empresas: " + e.getMessage());
        }
        return empresaList;
    }

    public Empresa getById(String id) {
        try {
            Document doc = database.getCollection(COLLECTION_NAME).find(Filters.eq("_id", new ObjectId(id))).first();
            return (doc != null) ? gson.fromJson(doc.toJson(), Empresa.class) : null;
        } catch (Exception e) {
            System.out.println("Erro ao buscar empresa por ID: " + e.getMessage());
            return null;
        }
    }

    public void create(Empresa empresa) {
        try {
            Document doc = Document.parse(gson.toJson(empresa));
            database.getCollection(COLLECTION_NAME).insertOne(doc);
            // Pega o _id gerado e seta no objeto Java
            empresa.setId(doc.getObjectId("_id").toHexString());
        } catch (Exception e) {
            System.out.println("Erro ao criar empresa: " + e.getMessage());
        }
    }

    public void update(String id, Empresa empresa) {
        try {
            Bson filtro = Filters.eq("_id", new ObjectId(id));
            Document novo = Document.parse(gson.toJson(empresa));
            novo.remove("_id");
            database.getCollection(COLLECTION_NAME).replaceOne(filtro, novo);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar empresa: " + e.getMessage());
        }
    }

    public void delete(String id) {
        try {
            database.getCollection(COLLECTION_NAME).deleteOne(Filters.eq("_id", new ObjectId(id)));
        } catch (Exception e) {
            System.out.println("Erro ao deletar empresa: " + e.getMessage());
        }
    }
}
