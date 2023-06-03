/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.mongoclient;

import org.bson.Document;

/**
 *
 * @author tdasilvamendonca
 */
public abstract class MongoClientConcession {
    private String url;

    public MongoClientConcession(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public abstract void getAll();
    public abstract String getOne(String id);
    public abstract String addOne(Document document);
    public abstract String updateOne(Document document, Document document2);
    public abstract String deleteOne(String id);
}
