/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.repository;

import jakarta.ws.rs.core.Response;
import org.bson.Document;
import io.github.cdimascio.dotenv.Dotenv;

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
    
    public abstract Response getAll();
    public abstract Response getOne(String id);
    public abstract Document getOneDocument(String id);
    public abstract Response addOne(Document document);
    public abstract Response updateOne(Document document, Document document2);
    public abstract Response deleteOne(String id);
}
