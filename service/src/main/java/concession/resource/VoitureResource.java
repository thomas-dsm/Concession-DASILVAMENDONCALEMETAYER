package concession.resource;

import concession.document.Caracteristique;
import concession.document.Voiture;
import concession.service.VoitureService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Path("/voiture")
public class VoitureResource {

    @Inject
    VoitureService service;

    @GET
    @Path("/getAll")
    @Produces(MediaType.TEXT_PLAIN)
    public List<String> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/get/{immat}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOne(String immat) {
        return service.getOne(immat);
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addOne(Voiture voiture) {

        Document caracteristique = new Document();
        caracteristique.append("puissance", voiture.getCaracteristiques().getPuissance());
        caracteristique.append("poids", voiture.getCaracteristiques().getPoids());
        caracteristique.append("longeur", voiture.getCaracteristiques().getLongueur());
        caracteristique.append("largeur", voiture.getCaracteristiques().getLargeur());
        caracteristique.append("carburant", voiture.getCaracteristiques().getCarburant());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        Document voitureDocument = new Document();

        voitureDocument.append("marque_id", new ObjectId(voiture.getMarqueId()));
        voitureDocument.append("immat", voiture.getImmat());
        voitureDocument.append("date_immat", voiture.getDateImmat());
        voitureDocument.append("prix", voiture.getPrix());
        voitureDocument.append("type", voiture.getType());
        voitureDocument.append("caracteristiques", caracteristique);
        voitureDocument.append("couleur", voiture.getCouleur());

        return service.addOne(voitureDocument);
    }
}
