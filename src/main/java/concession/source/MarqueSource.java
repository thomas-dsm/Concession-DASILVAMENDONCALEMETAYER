/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.source;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import concession.repository.MarqueRepository;
import concession.source.model.Marque;
import jakarta.enterprise.context.ApplicationScoped;
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

    public boolean addOne(Marque marque)
    {
        InsertOneResult result = repository.addOne(convertToDocument(marque));

        return result.wasAcknowledged();
    }

    public boolean updateOne(Marque marque, Marque oldMarque)
    {
        Document documentUpdate = new Document();
        documentUpdate.put("$set", convertToDocument(oldMarque));

        UpdateResult result = repository.updateOne(
                convertToDocument(marque),
                documentUpdate
        );

        return result.wasAcknowledged();
    }

    public boolean deleteOne(String nom)
    {
        DeleteResult result = repository.deleteOne(nom);

        return result.wasAcknowledged();
    }

    private Marque convertToMarque(Document docMarque)
    {
        return new Marque(
                docMarque.getString("nom"),
                docMarque.getInteger("anneeCreation"),
                docMarque.getString("pays")
        );
    }

    private Document convertToDocument(Marque marque)
    {
        Document marqueDocument = new Document();

        marqueDocument.append("nom", marque.getNom());
        marqueDocument.append("anneeCreation", marque.getAnneeCreation());
        marqueDocument.append("pays", marque.getPays());

        return marqueDocument;
    }
}
