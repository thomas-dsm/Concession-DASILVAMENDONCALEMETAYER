/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import java.util.List;

/**
 *
 * @author tdasilvamendonca
 */
public abstract class ConcessionRepository {
    private String url;

    public ConcessionRepository() {
        Dotenv dotenv = Dotenv.load();
        this.url = dotenv.get("MONGO");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public abstract List<Document> getAll();
    public abstract Document getOne(String id);
    public abstract InsertOneResult addOne(Document document);
    public abstract UpdateResult updateOne(Document document, Document document2);
    public abstract DeleteResult deleteOne(String id);
}
