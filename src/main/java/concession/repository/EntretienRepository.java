package concession.repository;

import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class EntretienRepository extends ConcessionRepository{
    @Override
    public List<Document> getAll() {

        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("entretiens");
            FindIterable<Document> results = collection.find();

            List<Document> listEntretien = new ArrayList<>();

            for (Document doc : results) {
                listEntretien.add(doc);
            }

            return listEntretien;
        }
    }

    @Override
    public Document getOne(String id) {
        return null;
    }

    @Override
    public InsertOneResult addOne(Document entretien) {

        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("entretiens");

            return collection.insertOne(entretien);
        }
    }

    @Override
    public UpdateResult updateOne(Document document, Document document2) {
        return null;
    }

    @Override
    public DeleteResult deleteOne(String id) {
        return null;
    }
}
