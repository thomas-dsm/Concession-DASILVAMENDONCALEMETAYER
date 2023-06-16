package concession.controller;

import concession.controller.dto.CaracteristiqueDTO;
import concession.controller.dto.VoitureDTO;
import concession.source.VoitureSource;
import concession.source.model.Caracteristique;
import concession.source.model.Voiture;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Path("/voiture")
public class VoitureController {

    @Inject
    VoitureSource source;

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VoitureDTO> getAll() {

        List<VoitureDTO> voitureDTOList = new ArrayList<>();
        List<Voiture> voitureList = source.getAll();

        for (Voiture voiture : voitureList){

            voitureDTOList.add(convertVoiture(voiture));
        }

        return voitureDTOList;
    }

    @GET
    @Path("/get/{immat}")
    @Produces(MediaType.APPLICATION_JSON)
    public VoitureDTO getOne(String immat) {

        Voiture voiture = source.getOne(immat);

        return convertVoiture(voiture);
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(VoitureDTO voiture) {

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

        return source.addOne(voitureDocument);
    }

    @PUT
    @Path("/update/{immat}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOne(VoitureDTO voiture, String immat) {
        /*
        Document oldVoiture = source.getOneDocument(immat);

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

        return source.updateOne(oldVoiture, documentUpdate);

         */
        return null;
    }

    @DELETE
    @Path("/delete/{immat}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOne(String immat) {
        return source.deleteOne(immat);
    }

    private VoitureDTO convertVoiture(Voiture voiture){

        return new VoitureDTO(
                voiture.getMarqueId(),
                voiture.getImmat(),
                voiture.getDateImmat(),
                voiture.getPrix(),
                voiture.getType(),
                convertCaracteristiques(voiture),
                voiture.getCouleur()
        );
    }

    private CaracteristiqueDTO convertCaracteristiques(Voiture voiture){

        return new CaracteristiqueDTO(
                voiture.getCaracteristiques().getPuissance(),
                voiture.getCaracteristiques().getPoids(),
                voiture.getCaracteristiques().getLongueur(),
                voiture.getCaracteristiques().getLargeur(),
                voiture.getCaracteristiques().getCarburant()
        );
    }
}
