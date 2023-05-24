package data;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

public class ConcessionData {
    private final MongoDatabase database;
    private final MongoCollection<Document> voitureCollection;
    private final MongoCollection<Document> marqueCollection;
    private final MongoCollection<Document> entretienCollection;

    public ConcessionData() {
        // Initialise la connexion à MongoDB
        String connectionString = "mongodb://localhost:27017";
        MongoClientURI uri = new MongoClientURI(connectionString);
        MongoClient mongoClient = new MongoClient(uri);

        // Obtient une référence à la base de données
        this.database = mongoClient.getDatabase("Concession");

        // Obtient une référence aux collections
        this.voitureCollection = database.getCollection("Voiture");
        this.marqueCollection = database.getCollection("Marque");
        this.entretienCollection = database.getCollection("Entretien");
    }

    public void createDocuments() {
        // Crée 10 documents pour chaque collection
        createVoitureDocuments();
        createMarqueDocuments();
        createEntretienDocuments();
    }

    private void createVoitureDocuments() {
        for (int i = 0; i < 10; i++) {
            Document document = new Document();
            document.append("marque_id", new ObjectId("OOO1"));
            document.append("immat", "AA-000-AA");
            document.append("date_immat", "2012-03-26T23:20:16.000+00:00");
            document.append("prix", 40000);
            document.append("type", Arrays.asList("sportive", "citadine"));

            Document caracteristiques = new Document();
            caracteristiques.append("puissance", 1000);
            caracteristiques.append("poids", 2000);
            caracteristiques.append("longueur", 5);
            caracteristiques.append("largeur", 3);
            caracteristiques.append("carburant", "ethanol");
            document.append("caracteristiques", caracteristiques);

            document.append("couleur", "FFFFFF");

            voitureCollection.insertOne(document);
        }

        System.out.println("Documents Voiture créés avec succès");
    }

    private void createMarqueDocuments() {
        for (int i = 0; i < 10; i++) {
            Document document = new Document();
            document.append("nom", "FIAT");
            document.append("annee_creation", 1899);
            document.append("pays", "Italie");

            marqueCollection.insertOne(document);
        }

        System.out.println("Documents Marque créés avec succès");
    }

    private void createEntretienDocuments() {
        for (int i = 0; i < 10; i++) {
            Document document = new Document();
            document.append("voiture_id", new ObjectId("OOO1"));
            document.append("date", "2012-03-26T23:20:16.000+00:00");
            document.append("description", "vidange");
            document.append("garage", "Garage de José");

            entretienCollection.insertOne(document);
        }

        System.out.println("Documents Entretien créés avec succès");
    }
}