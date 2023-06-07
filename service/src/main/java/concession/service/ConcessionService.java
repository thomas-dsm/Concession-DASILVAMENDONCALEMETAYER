/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.service;

import org.bson.Document;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

/**
 *
 * @author tdasilvamendonca
 */
public abstract class ConcessionService {
    private String url;

    public ConcessionService() {
        Dotenv dotenv = Dotenv.load();
        this.url = dotenv.get("MONGO");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public abstract List<String> getAll();
    public abstract String getOne(String id);
    public abstract String addOne(Document document);
    public abstract String updateOne(Document document, Document document2);
    public abstract String deleteOne(String id);
}