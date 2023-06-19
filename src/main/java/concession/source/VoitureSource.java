/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.source;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import concession.repository.VoitureRepository;
import concession.source.model.Caracteristique;
import concession.source.model.Voiture;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.types.ObjectId;

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

    public boolean addOne(Voiture voiture)
    {
        InsertOneResult result = repository.addOne(convertToDocument(voiture));

        return result.wasAcknowledged();
    }

    public boolean updateOne(Voiture voiture, Voiture oldVoiture)
    {
        Document documentUpdate = new Document();
        documentUpdate.put("$set", convertToDocument(oldVoiture));

        UpdateResult result = repository.updateOne(
                convertToDocument(voiture),
                documentUpdate
        );

        return result.wasAcknowledged();
    }

    public boolean deleteOne(String immat)
    {
        DeleteResult result = repository.deleteOne(immat);

        return result.wasAcknowledged();
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

    private Document convertToDocument(Voiture voiture)
    {
        Document voitureDocument = new Document();

        voitureDocument.append("marque_id", new ObjectId(voiture.getMarqueId()));
        voitureDocument.append("immat", voiture.getImmat());
        voitureDocument.append("date_immat", voiture.getDateImmat());
        voitureDocument.append("prix", voiture.getPrix());
        voitureDocument.append("type", voiture.getType());
        voitureDocument.append("caracteristiques", convertToDocument(voiture.getCaracteristiques()));
        voitureDocument.append("couleur", voiture.getCouleur());

        return voitureDocument;
    }

    private Document convertToDocument(Caracteristique caracteristique)
    {
        Document caracteristiqueDocument = new Document();

        caracteristiqueDocument.append("puissance", caracteristique.getPuissance());
        caracteristiqueDocument.append("poids", caracteristique.getPoids());
        caracteristiqueDocument.append("longeur", caracteristique.getLongueur());
        caracteristiqueDocument.append("largeur", caracteristique.getLargeur());
        caracteristiqueDocument.append("carburant", caracteristique.getCarburant());

        return caracteristiqueDocument;
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
