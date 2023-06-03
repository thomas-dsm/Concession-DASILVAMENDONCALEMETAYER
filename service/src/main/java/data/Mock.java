/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import concession.document.Caracteristique;
import concession.document.Voiture;
import concession.mongoclient.MongoClientVoiture;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author tdasilvamendonca
 */
public class Mock {
    public static void MockVoiture(MongoClientVoiture voitureClient){
        //Montre toutes les Voitures
        voitureClient.getAll();
        
        //Initialisation de la voiture
        Caracteristique caracteristiques = new Caracteristique(1000, 1000, 5, 2, "diesel");
        Document docCaracteristiques = new Document();
        docCaracteristiques.append("puissance", caracteristiques.getPuissance());
        docCaracteristiques.append("poids", caracteristiques.getPoids());
        docCaracteristiques.append("longueur", caracteristiques.getLongueur());
        docCaracteristiques.append("largeur", caracteristiques.getLargeur());
        docCaracteristiques.append("carburant", caracteristiques.getCarburant());
        
        List<String> type = new ArrayList();
        type.add("sportive");
        type.add("suv");
        
        Voiture voiture = new Voiture("646e054c9069318e382b32b7","TE-000-ST", "2023-06-02T14:55:32.000+00:00", 4000, type, caracteristiques, "FFF");
        
        Document docVoiture = new Document();
        docVoiture.append("marque_id", new ObjectId(voiture.getMarqueId()));
        docVoiture.append("immat", voiture.getImmat());
        docVoiture.append("date_immat", voiture.getDateImmat());
        docVoiture.append("prix", voiture.getPrix());
        docVoiture.append("type", voiture.getType());
        docVoiture.append("caracteristiques", docCaracteristiques);
        docVoiture.append("couleur", voiture.getCouleur());
        
        //Ajoute la voiture
        System.out.println(voitureClient.addOne(docVoiture));
        
        //Modification de la voiture
        Document docUpdVoiture = new Document();
        docUpdVoiture.put("prix", 2000);
        
        Document documentUpdate = new Document();
        documentUpdate.put("$set", docUpdVoiture);
        
        System.out.println(voitureClient.updateOne(docVoiture, documentUpdate));
        
        
        System.out.println(voitureClient.getOne("TE-000-ST"));
        
        //Suppression de la voiture
        System.out.println(voitureClient.deleteOne("TE-000-ST"));
    }
}
