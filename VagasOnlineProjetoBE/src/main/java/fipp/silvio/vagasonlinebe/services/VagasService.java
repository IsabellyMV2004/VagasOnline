package fipp.silvio.vagasonlinebe.services;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import fipp.silvio.vagasonlinebe.entities.Interesse;
import fipp.silvio.vagasonlinebe.entities.Vaga;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VagasService extends BaseMongoService {

    private final Gson gson = new Gson();
    private static final String COLLECTION_VAGAS = "vagas";
    private static final String COLLECTION_INTERESSES = "interesses";

    public VagasService(MongoClient mongoClient) {
        super(mongoClient);
    }

    public List<Vaga> getAll() {
        List<Vaga> vagaList = new ArrayList<>();
        try (MongoCursor<Document> cursor = database.getCollection(COLLECTION_VAGAS).find().iterator()) {
            while (cursor.hasNext())
                vagaList.add(gson.fromJson(cursor.next().toJson(), Vaga.class));
        } catch (Exception e) {
            System.out.println("Erro ao buscar vagas: " + e.getMessage());
        }
        return vagaList;
    }

    public Vaga getById(String id) {
        try {
            Document doc = database.getCollection(COLLECTION_VAGAS).find(Filters.eq("_id", new ObjectId(id))).first();
            return (doc != null) ? gson.fromJson(doc.toJson(), Vaga.class) : null;
        } catch (Exception e) {
            System.out.println("Erro ao buscar vaga por ID: " + e.getMessage());
            return null;
        }
    }

    public void create(Vaga vaga) {
        try {
            database.getCollection(COLLECTION_VAGAS).insertOne(Document.parse(gson.toJson(vaga)));
        } catch (Exception e) {
            System.out.println("Erro ao criar vaga: " + e.getMessage());
        }
    }

    public void update(String id, Vaga vaga) {
        try {
            Bson filtro = Filters.eq("_id", new ObjectId(id));
            Document novo = Document.parse(gson.toJson(vaga));
            novo.remove("_id");
            database.getCollection(COLLECTION_VAGAS).replaceOne(filtro, novo);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar vaga: " + e.getMessage());
        }
    }

    public void delete(String id) {
        try {
            database.getCollection(COLLECTION_VAGAS)
                    .deleteOne(Filters.eq("_id", new ObjectId(id)));
        } catch (Exception e) {
            System.out.println("Erro ao deletar vaga: " + e.getMessage());
        }
    }

    public void registrarInteresse(Interesse interesse) {
        try {
            database.getCollection(COLLECTION_INTERESSES).insertOne(Document.parse(gson.toJson(interesse)));
        } catch (Exception e) {
            System.out.println("Erro ao registrar interesse: " + e.getMessage());
        }
    }
}
