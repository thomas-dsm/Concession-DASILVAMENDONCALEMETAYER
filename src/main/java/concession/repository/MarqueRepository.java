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

public class MarqueRepository extends ConcessionRepository {
    @Override
    public List<Document> getAll()
    {
        try (MongoClient mongoClient = MongoClients.create(getUrl()))
        {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");
            FindIterable<Document> results = collection.find();

            List<Document> listMarque = new ArrayList<>();

            for (Document doc : results) {
                listMarque.add(doc);
            }

            return listMarque;
        }
    }

    @Override
    public Document getOne(String nom)
    {
        Bson filter = Filters.regex("nom", nom);

        try (MongoClient mongoClient = MongoClients.create(getUrl()))
        {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");

            return collection.find(filter).first();
        }
    }

    @Override
    public InsertOneResult addOne(Document marque)
    {
        try (MongoClient mongoClient = MongoClients.create(getUrl()))
        {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");

            return collection.insertOne(marque);
        }
    }

    @Override
    public UpdateResult updateOne(Document marqueQuery, Document marqueUpdate)
    {
        try (MongoClient mongoClient = MongoClients.create(getUrl()))
        {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");

            return collection.updateOne(marqueQuery, marqueUpdate);
        }
    }

    @Override
    public DeleteResult deleteOne(String nom)
    {
        Bson filter = Filters.regex("nom", nom);

        try (MongoClient mongoClient = MongoClients.create(getUrl())) {
            MongoDatabase database = mongoClient.getDatabase("concession");
            MongoCollection<Document> collection = database.getCollection("marques");

            return collection.deleteOne(filter);
        }
    }
}
