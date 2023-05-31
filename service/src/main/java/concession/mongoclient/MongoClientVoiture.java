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
import org.bson.Document;
import org.bson.conversions.Bson;
import static org.springframework.core.convert.TypeDescriptor.collection;

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
                for(Document doc : results){
                    System.out.println(doc.toJson());
                }
            } else {
                System.out.println("No matching voiture found.");
            }
        }
    }

    @Override
    public void getOne(String immat) {

        Bson filter = Filters.regex("immat", immat);
        
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            Document doc = collection.find(filter).first();
            
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching voiture found.");
            }
        }
    }

    @Override
    public void addOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
