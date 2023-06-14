/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
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
public class EntretienRepository extends ConcessionRepository {

    @Override
    public Response getAll() {
        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("entretiens");
            FindIterable<Document> results = collection.find();

            return Response.ok().entity(results).build();
        }
    }

    @Override
    public Response getOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Document getOneDocument(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Response addOne(Document entretien) {

        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("entretiens");

            return Response.status(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response updateOne(Document entretien, Document entretien2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Response deleteOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Response getAllByVoiture(String immat) {

        VoitureRepository voitureService = new VoitureRepository();
        Document voiture = voitureService.getOneDocument(immat);

        if (voiture == null){
            return Response.status(404).build();
        }

        Bson filter = Filters.eq("voiture_id", voiture.getObjectId("_id"));

        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("entretiens");
            FindIterable<Document> results = collection.find(filter);

            List<String> listEntretiens = new ArrayList<>();

            for (Document doc : results) {
                listEntretiens.add(doc.toJson());
            }

            return Response.ok().entity(listEntretiens).build();
        }
    }

    public Response addOneByVoiture(Document entretien, String immat) {

        VoitureRepository voitureService = new VoitureRepository();
        Document voiture = voitureService.getOneDocument(immat);

        if (voiture == null){
            return Response.status(404).build();
        }

        entretien.append("voiture_id", voiture.getObjectId("_id"));

        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("entretiens");
            collection.insertOne(entretien);

            return Response.status(Response.Status.CREATED).build();
        }
    }
}
