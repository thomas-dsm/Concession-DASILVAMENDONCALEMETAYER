/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.source;

import concession.repository.MarqueRepository;
import concession.source.model.Marque;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tdasilvamendonca
 */
@ApplicationScoped
public class MarqueSource {

    MarqueRepository repository = new MarqueRepository();

    public List<Marque> getAll() {

        List<Marque> listMarque = new ArrayList<>();
        List<Document> listDocMarque = repository.getAll();

        for (Document docMarque : listDocMarque)
        {
            listMarque.add(convertToMarque(docMarque));
        }

        return listMarque;
    }

    public Marque getOne(String nom)
    {
        return convertToMarque(repository.getOne(nom));
    }


    public Document getOneDocument(String nom) {
        return null;
    }


    public Response addOne(Document marque) {
        return null;
    }


    public Response updateOne(Document marque, Document marque2) {
        return null;
    }


    public Response deleteOne(String nom) {
        return null;
    }

    private Marque convertToMarque(Document docMarque)
    {
        return new Marque(
                docMarque.getString("nom"),
                docMarque.getInteger("annee_creation"),
                docMarque.getString("pays")
        );
    }
}
