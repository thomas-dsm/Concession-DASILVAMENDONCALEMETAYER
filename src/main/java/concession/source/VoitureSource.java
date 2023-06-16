/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.source;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import concession.repository.VoitureRepository;
import concession.source.model.Caracteristique;
import concession.source.model.Voiture;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tdasilvamendonca
 */
@ApplicationScoped
public class VoitureSource {

    VoitureRepository repository = new VoitureRepository();

    public List<Voiture> getAll()
    {
        List<Voiture> listVoiture = new ArrayList<>();
        List<Document> listDocVoiture = repository.getAll();

        for (Document docVoiture : listDocVoiture) {
            listVoiture.add(convertToVoiture(docVoiture));
        }

        return listVoiture;
    }

    public Voiture getOne(String immat)
    {
        return convertToVoiture(repository.getOne(immat));
    }

    public Response addOne(Document voiture) {
        return null;
    }

    public Response updateOne(Document voiture, Document voiture2) {
        return null;
    }

    public Response deleteOne(String immat) {

        return null;
    }

    private Voiture convertToVoiture(Document docVoiture){

        return new Voiture(
                docVoiture.getObjectId("marque_id").toString(),
                docVoiture.getString("immat"),
                docVoiture.getDate("date_immat"),
                docVoiture.getInteger("prix"),
                docVoiture.getList("type", String.class),
                convertToCaracteristique(docVoiture.get("caracteristiques", Document.class)),
                docVoiture.getString("couleur")
        );
    }

    private Caracteristique convertToCaracteristique(Document docCaracteristique){

        return new Caracteristique(
                docCaracteristique.getInteger("puissance"),
                docCaracteristique.getInteger("poids"),
                docCaracteristique.getInteger("longueur"),
                docCaracteristique.getInteger("largeur"),
                docCaracteristique.getString("carburant")
        );
    }
}
