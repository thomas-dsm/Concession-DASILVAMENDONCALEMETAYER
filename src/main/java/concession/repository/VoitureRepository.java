package concession.repository;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class VoitureRepository extends ConcessionRepository {
    public List<Document> getAll() {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            FindIterable<Document> results = collection.find();

            List<Document> listVoiture = new ArrayList<>();

            for (Document doc : results) {
                listVoiture.add(doc);
            }

            return listVoiture;
        }
    }

    @Override
    public Document getOne(String immat) {

        Bson filter = Filters.regex("immat", immat);

        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            return collection.find(filter).first();
        }
    }

    @Override
    public InsertOneResult addOne(Document voiture) {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            return collection.insertOne(voiture);
        }
    }

    @Override
    public UpdateResult updateOne(Document voiture, Document voiture2) {

        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");

            return collection.updateOne(voiture, voiture2);
        }
    }

    @Override
    public DeleteResult deleteOne(String immat) {
        Bson filter = Filters.regex("immat", immat);

        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            return collection.deleteOne(filter);
        }
    }
}
