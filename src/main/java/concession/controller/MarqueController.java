package concession.controller;

import concession.controller.dto.MarqueDTO;
import concession.source.MarqueSource;
import concession.source.model.Marque;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Path("/marque")
public class MarqueController {

    @Inject
    MarqueSource source;

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MarqueDTO> getAll() {

        List<MarqueDTO> marqueDTOList = new ArrayList<>();
        List<Marque> marqueList = source.getAll();

        for (Marque marque : marqueList)
        {
            marqueDTOList.add(convertMarque(marque));
        }

        return marqueDTOList;
    }

    @GET
    @Path("/get/{nom}")
    @Produces(MediaType.APPLICATION_JSON)
    public MarqueDTO getOne(String nom) {

        return convertMarque(source.getOne(nom));
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(MarqueDTO marque) {

        Document marqueDocument = new Document();

        marqueDocument.append("nom", marque.getNom());
        marqueDocument.append("anneeCreation", marque.getAnneeCreation());
        marqueDocument.append("pays", marque.getPays());

        return source.addOne(marqueDocument);
    }

    @PUT
    @Path("/update/{nom}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOne(MarqueDTO marque, String nom) {

        Document oldMarque = source.getOneDocument(nom);

        Document marqueDocument = new Document();

        marqueDocument.append("nom", marque.getNom());
        marqueDocument.append("anneeCreation", marque.getAnneeCreation());
        marqueDocument.append("pays", marque.getPays());

        Document documentUpdate = new Document();
        documentUpdate.put("$set", marqueDocument);

        return source.updateOne(oldMarque, documentUpdate);
    }

    @DELETE
    @Path("/delete/{nom}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOne(String nom) {
        return source.deleteOne(nom);
    }

    private MarqueDTO convertMarque(Marque marque)
    {
        return new MarqueDTO(
                marque.getNom(),
                marque.getAnneeCreation(),
                marque.getPays()
        );
    }
}
