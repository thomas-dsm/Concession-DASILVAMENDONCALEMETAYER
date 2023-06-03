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
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

/**
 *
 * @author tdasilvamendonca
 */
public class MongoClientEntretien extends MongoClientConcession {
    
    public MongoClientEntretien(String url) {
        super(url);
    }

    @Override
    public void getAll() {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("entretiens");
            FindIterable<Document> results = collection.find();

            if (results != null) {
                for (Document doc : results) {
                    System.out.println(doc.toJson());
                }
            } else {
                System.out.println("No matching entretien found.");
            }
        }
    }

    @Override
    public String getOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String addOne(Document entretien) {

        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("entretiens");
            InsertOneResult results = collection.insertOne(entretien);
            
            if (results != null) {
                return "Entretien : " + results.getInsertedId().asObjectId().getValue().toString() +  " was added to collection";
            } else {
                return "Cannot update entretien : " + entretien.get("immat");
            }
        }
    }

    @Override
    public String updateOne(Document entretien, Document entretien2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String deleteOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
