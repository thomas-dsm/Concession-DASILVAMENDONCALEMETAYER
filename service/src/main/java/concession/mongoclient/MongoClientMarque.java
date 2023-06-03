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
public class MongoClientMarque extends MongoClientConcession {
    
    public MongoClientMarque(String url) {
        super(url);
    }

    @Override
    public void getAll() {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");
            FindIterable<Document> results = collection.find();

            if (results != null) {
                for (Document doc : results) {
                    System.out.println(doc.toJson());
                }
            } else {
                System.out.println("No matching marques found.");
            }
        }
    }

    @Override
    public String getOne(String nom) {
        Bson filter = Filters.regex("nom", nom);
        
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");
            Document doc = collection.find(filter).first();
            
            if (doc != null) {
                return doc.toJson();
            } else {
                return "No matching marques found.";
            }
        }
    }

    @Override
    public String addOne(Document marque) {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");
            InsertOneResult results = collection.insertOne(marque);
            
            if (results != null) {
                return "Marque : " + results.getInsertedId().asObjectId().getValue().toString() +  " was added to collection";
            } else {
                return "Cannot update marque : " + marque.get("nom");
            }
        }    
    }

    @Override
    public String updateOne(Document marque, Document marque2) {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");
            
            UpdateResult results = collection.updateOne(marque, marque2);
            
            if (results.getMatchedCount() == 1) {
                return "Marque was modified";
            } else {
                return "Cannot add marque.";
            }
        }
    }

    @Override
    public String deleteOne(String nom) {
        Bson filter = Filters.regex("nom", nom);
        
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");
            DeleteResult results = collection.deleteOne(filter);

            if (results.getDeletedCount() == 1) {
                return "Marque " + nom + " was deleted successfully";
                
            } else {
                return "No matching marque found.";
            }
        }    
    }
}
