/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.source;

import com.mongodb.client.result.InsertOneResult;
import concession.repository.EntretienRepository;
import concession.source.model.Entretien;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

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

    public boolean addOneByVoiture(Entretien entretien, String immat)
    {
        InsertOneResult result = repository.addOneByVoiture(convertToDocument(entretien), immat);

        return result.wasAcknowledged();
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

    private Document convertToDocument(Entretien entretien)
    {
        Document entretienDocument = new Document();

        entretienDocument.append("date", entretien.getDate());
        entretienDocument.append("description", entretien.getDescription());
        entretienDocument.append("garage", entretien.getGarage());

        return entretienDocument;
    }
}
