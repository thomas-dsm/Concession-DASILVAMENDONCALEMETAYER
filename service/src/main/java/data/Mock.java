/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import concession.document.Caracteristique;
import concession.document.Entretien;
import concession.document.Marque;
import concession.document.Voiture;
import concession.mongoclient.MongoClientEntretien;
import concession.mongoclient.MongoClientMarque;
import concession.mongoclient.MongoClientVoiture;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author tdasilvamendonca
 */
public class Mock {
    public static void MockVoiture(MongoClientVoiture voitureClient){
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");  

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
        
        Voiture voiture = new Voiture("646e054c9069318e382b32b7","TE-000-ST", new Date(), 4000, type, caracteristiques, "FFF");
        
        Document docVoiture = new Document();
        docVoiture.append("marque_id", new ObjectId(voiture.getMarqueId()));
        docVoiture.append("immat", voiture.getImmat());
        docVoiture.append("date_immat", dateFormat.format(voiture.getDateImmat()));
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
    
    public static void MockMarque(MongoClientMarque marqueClient){
   
        marqueClient.getAll();
        
        //Initialisation de la marque
        Marque marque = new Marque("Vilebrequin", 2023, "France");
        
        Document docMarque = new Document();
        docMarque.append("nom", marque.getNom());
        docMarque.append("annee_creation", marque.getAnneeCreation());
        docMarque.append("pays", marque.getPays());
        
        //Ajoute la marque
        System.out.println(marqueClient.addOne(docMarque));
        
        //Modification de la marque
        Document docUpdMarque = new Document();
        docUpdMarque.put("pays", "Merguez");
        
        Document documentUpdate = new Document();
        documentUpdate.put("$set", docUpdMarque);
        
        System.out.println(marqueClient.updateOne(docMarque, documentUpdate));
        
        //affiche la nouvelle marque
        System.out.println(marqueClient.getOne("Vilebrequin"));
        
        //Suppression de la marque
        System.out.println(marqueClient.deleteOne("Vilebrequin"));
    }

    public static void MockEntretien(MongoClientEntretien entretienClient) {
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");  
        
        //affiche tous les entretiens
        entretienClient.getAll();
        
        Entretien entretien = new Entretien("646dfb289069318e382b3299", new Date(), "entretien annuelle", "WAuto");

        Document docEntretien = new Document();
        docEntretien.append("voiture_id", new ObjectId(entretien.getVoitureId()));
        docEntretien.append("date", dateFormat.format(entretien.getDate()));
        docEntretien.append("description", entretien.getDescription());
        docEntretien.append("garage", entretien.getGarage());

        //Ajoute l'entretien
        System.out.println(entretienClient.addOne(docEntretien));
    }
}
