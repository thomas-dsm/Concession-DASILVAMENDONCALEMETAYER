/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.mongoclient;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author tdasilvamendonca
 */
public class MongoClientVoiture extends MongoClientConcession {

    public MongoClientVoiture(String url) {
        super(url);
    }

    @Override
    public void getAll() {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            FindIterable<Document> results = collection.find();

            if (results != null) {
                for (Document doc : results) {
                    System.out.println(doc.toJson());
                }
            } else {
                System.out.println("No matching voitures found.");
            }
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
            
            if (results != null) {
                return "Voiture : " + results.getInsertedId().asObjectId().getValue().toString() +  " was added to collection";
            } else {
                return "Cannot update voiture : " + voiture.get("immat");
            }
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
