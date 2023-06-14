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
import com.mongodb.client.result.UpdateResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tdasilvamendonca
 */
@ApplicationScoped
public class VoitureService extends ConcessionService {

    @Override
    public Response getAll() {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            FindIterable<Document> results = collection.find();

            List<String> listVoiture = new ArrayList<>();

            for (Document doc : results) {
                listVoiture.add(doc.toJson());
            }

            return Response.ok().entity(listVoiture).build();
        }
    }

    @Override
    public Response getOne(String immat) {
        Bson filter = Filters.regex("immat", immat);
        
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            Document doc = collection.find(filter).first();

            if (doc != null) {
                return Response.ok().entity(doc.toJson()).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
    }
    @Override
    public Document getOneDocument(String immat) {

        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            Bson filter = Filters.regex("immat", immat);

            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");

            return collection.find(filter).first();
        }
    }

    @Override
    public Response addOne(Document voiture) {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            collection.insertOne(voiture);

            return Response.status(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response updateOne(Document voiture, Document voiture2) {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            
            UpdateResult results = collection.updateOne(voiture, voiture2);

            if (results.getMatchedCount() == 1) {
                return Response.status(Response.Status.NO_CONTENT).build();
            } else {
                return Response.status(Response.Status.NOT_MODIFIED).build();
            }
        } catch (IllegalArgumentException exception){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response deleteOne(String immat) {
        
        Bson filter = Filters.regex("immat", immat);
        
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("voitures");
            DeleteResult results = collection.deleteOne(filter);

            if (results.getDeletedCount() == 1) {
                return Response.status(Response.Status.NO_CONTENT).build();

            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
    }
}
