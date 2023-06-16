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
import concession.repository.EntretienRepository;
import concession.source.model.Entretien;
import concession.source.model.Voiture;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tdasilvamendonca
 */
@ApplicationScoped
public class EntretienSource {

    EntretienRepository repository = new EntretienRepository();

    public List<Entretien> getAll()
    {
        List<Entretien> listEntretien = new ArrayList<>();
        List<Document> listDocEntretien = repository.getAll();

        for (Document docEntretien : listDocEntretien) {

            listEntretien.add(convertToEntretien(docEntretien));
        }

        return listEntretien;
    }

    public List<Entretien> getAllByVoiture(String immat) {

        List<Entretien> listEntretien = new ArrayList<>();
        List<Document> listDocEntretien = repository.getAllByVoitureImmat(immat);

        for (Document docEntretien : listDocEntretien)
        {
            listEntretien.add(convertToEntretien(docEntretien));
        }

        return listEntretien;
    }

    public Response addOneByVoiture(Document entretien, String immat)
    {
        return null;
    }

    private Entretien convertToEntretien(Document docEntretien)
    {
        return new Entretien(
                docEntretien.getObjectId("voiture_id").toString(),
                docEntretien.getDate("date"),
                docEntretien.getString("description"),
                docEntretien.getString("garage")
        );
    }
}
