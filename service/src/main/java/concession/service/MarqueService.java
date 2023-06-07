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
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tdasilvamendonca
 */
public class MarqueService extends ConcessionService {

    @Override
    public Response getAll() {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");
            FindIterable<Document> results = collection.find();

            List<String> listMarque = new ArrayList<>();

            for (Document doc : results) {
                listMarque.add(doc.toJson());
            }

            return Response.ok().entity(listMarque).build();
        }
    }

    @Override
    public Response getOne(String nom) {
        Bson filter = Filters.regex("nom", nom);
        
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");
            Document doc = collection.find(filter).first();

            if (doc != null) {
                return Response.ok().entity(doc.toJson()).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
    }

    @Override
    public Document getOneDocument(String nom) {
        Bson filter = Filters.regex("nom", nom);

        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");

            return collection.find(filter).first();
        }
    }

    @Override
    public Response addOne(Document marque) {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");
            InsertOneResult results = collection.insertOne(marque);

            return Response.status(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response updateOne(Document marque, Document marque2) {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");
            
            UpdateResult results = collection.updateOne(marque, marque2);
            
            if (results.getMatchedCount() == 1) {
                return Response.status(Response.Status.NO_CONTENT).build();
            } else {
                return Response.status(Response.Status.NOT_MODIFIED).build();
            }
        }
    }

    @Override
    public Response deleteOne(String nom) {
        Bson filter = Filters.regex("nom", nom);
        
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");
            DeleteResult results = collection.deleteOne(filter);

            if (results.getDeletedCount() == 1) {
                return Response.status(Response.Status.NO_CONTENT).build();
                
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }    
    }
}
