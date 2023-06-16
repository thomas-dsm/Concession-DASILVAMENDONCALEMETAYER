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

public class EntretienRepository extends ConcessionRepository{
    @Override
    public List<Document> getAll()
    {
        try (MongoClient mongoClient = MongoClients.create(getUrl()))
        {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("entretiens");
            FindIterable<Document> results = collection.find();

            List<Document> listEntretien = new ArrayList<>();

            for (Document doc : results) {
                listEntretien.add(doc);
            }

            return listEntretien;
        }
    }

    public List<Document> getAllByVoitureImmat(String immat)
    {
        VoitureRepository voitureRepository = new VoitureRepository();
        Document voiture = voitureRepository.getOne(immat);

        Bson filter = Filters.eq("voiture_id", voiture.getObjectId("_id"));

        try (MongoClient mongoClient = MongoClients.create(getUrl()))
        {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("entretiens");
            FindIterable<Document> results = collection.find(filter);

            List<Document> listEntretien = new ArrayList<>();

            for (Document doc : results) {
                listEntretien.add(doc);
            }

            return listEntretien;
        }
    }

    @Override
    public Document getOne(String id)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public InsertOneResult addOne(Document entretien) {

        try (MongoClient mongoClient = MongoClients.create(getUrl()))
        {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("entretiens");

            return collection.insertOne(entretien);
        }
    }

    public InsertOneResult addOneByVoiture(Document entretien, String immat)
    {
        VoitureRepository voitureRepository = new VoitureRepository();
        Document voiture = voitureRepository.getOne(immat);

        entretien.append("voiture_id", voiture.getObjectId("_id"));

        try (MongoClient mongoClient = MongoClients.create(getUrl()))
        {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("entretiens");

            return  collection.insertOne(entretien);
        }
    }

    @Override
    public UpdateResult updateOne(Document document, Document document2)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DeleteResult deleteOne(String id)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
