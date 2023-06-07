package concession.resource;

import concession.document.Voiture;
import concession.service.VoitureService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.bson.types.ObjectId;

@Path("/voiture")
public class VoitureResource {

    @Inject
    VoitureService service;

    @GET
    @Path("/getAll")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAll() {
        return service.getAll();
    }

    @GET
    @Path("/get/{immat}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getOne(String immat) {
        return service.getOne(immat);
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(Voiture voiture) {

        Document caracteristique = new Document();
        caracteristique.append("puissance", voiture.getCaracteristiques().getPuissance());
        caracteristique.append("poids", voiture.getCaracteristiques().getPoids());
        caracteristique.append("longeur", voiture.getCaracteristiques().getLongueur());
        caracteristique.append("largeur", voiture.getCaracteristiques().getLargeur());
        caracteristique.append("carburant", voiture.getCaracteristiques().getCarburant());

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

    @PUT
    @Path("/update/{immat}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOne(Voiture voiture, String immat) {

        Document oldVoiture = service.getOneDocument(immat);

        Document caracteristique = new Document();
        caracteristique.append("puissance", voiture.getCaracteristiques().getPuissance());
        caracteristique.append("poids", voiture.getCaracteristiques().getPoids());
        caracteristique.append("longeur", voiture.getCaracteristiques().getLongueur());
        caracteristique.append("largeur", voiture.getCaracteristiques().getLargeur());
        caracteristique.append("carburant", voiture.getCaracteristiques().getCarburant());

        Document voitureDocument = new Document();

        voitureDocument.put("marque_id", new ObjectId(voiture.getMarqueId()));
        voitureDocument.put("immat", voiture.getImmat());
        voitureDocument.put("date_immat", voiture.getDateImmat());
        voitureDocument.put("prix", voiture.getPrix());
        voitureDocument.put("type", voiture.getType());
        voitureDocument.put("caracteristiques", caracteristique);
        voitureDocument.put("couleur", voiture.getCouleur());

        Document documentUpdate = new Document();
        documentUpdate.put("$set", voitureDocument);

        return service.updateOne(oldVoiture, documentUpdate);
    }

    @DELETE
    @Path("/delete/{immat}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteOne(String immat) {
        return service.deleteOne(immat);
    }
}
