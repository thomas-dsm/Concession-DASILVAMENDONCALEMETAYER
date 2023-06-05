/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tdasilvamendonca
 */
@ApplicationScoped
public class VoitureService extends ConcessionService {

    @Override
    public List<String> getAll() {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            FindIterable<Document> results = collection.find();

            List<String> listVoiture = new ArrayList<>();

            for (Document doc : results) {
                listVoiture.add(doc.toJson());
            }

            return listVoiture;
        }
    }

    @Override
    public String getOne(String immat) {
        Bson filter = Filters.regex("immat", immat);
        
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            Document doc = collection.find(filter).first();
            
            if (doc != null) {
                return doc.toJson();
            } else {
                return "No matching voiture found.";
            }
        }
    }

    @Override
    public String addOne(Document voiture) {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            InsertOneResult results = collection.insertOne(voiture);

            return "Voiture : " + Objects.requireNonNull(results.getInsertedId()).asObjectId().getValue().toString() + " was added to collection";
        }
    }

    @Override
    public String updateOne(Document voiture, Document voiture2) {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            
            UpdateResult results = collection.updateOne(voiture, voiture2);
            
            if (results.getMatchedCount() == 1) {
                return "Voiture was modified";
            } else {
                return "Cannot add voiture.";
            }
        }
    }

    @Override
    public String deleteOne(String immat) {
        
        Bson filter = Filters.regex("immat", immat);
        
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            DeleteResult results = collection.deleteOne(filter);

            if (results.getDeletedCount() == 1) {
                return "Voiture " + immat + " was deleted successfully";
                
            } else {
                return "No matching voiture found.";
            }
        }
    }
}
