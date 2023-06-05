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
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author tdasilvamendonca
 */
public class MarqueService extends ConcessionService {

    @Override
    public List<String> getAll() {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");
            FindIterable<Document> results = collection.find();

            for (Document doc : results) {
                System.out.println(doc.toJson());
            }
        }
        return null;
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

            return "Marque : " + Objects.requireNonNull(results.getInsertedId()).asObjectId().getValue().toString() + " was added to collection";
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
