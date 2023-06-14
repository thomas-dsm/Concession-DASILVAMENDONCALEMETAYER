package concession.resource;

import concession.document.Marque;
import concession.service.MarqueService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;

@Path("/marque")
public class MarqueResource {

    @Inject
    MarqueService service;

    @GET
    @Path("/getAll")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAll() {
        return service.getAll();
    }

    @GET
    @Path("/get/{nom}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getOne(String nom) {
        return service.getOne(nom);
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(Marque marque) {
        Document marqueDocument = new Document();

        marqueDocument.append("nom", marque.getNom());
        marqueDocument.append("anneeCreation", marque.getAnneeCreation());
        marqueDocument.append("pays", marque.getPays());

        return service.addOne(marqueDocument);
    }

    @PUT
    @Path("/update/{nom}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOne(Marque marque, String nom) {

        Document oldMarque = service.getOneDocument(nom);

        Document marqueDocument = new Document();

        marqueDocument.append("nom", marque.getNom());
        marqueDocument.append("anneeCreation", marque.getAnneeCreation());
        marqueDocument.append("pays", marque.getPays());

        Document documentUpdate = new Document();
        documentUpdate.put("$set", marqueDocument);

        return service.updateOne(oldMarque, documentUpdate);
    }

    @DELETE
    @Path("/delete/{nom}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteOne(String nom) {
        return service.deleteOne(nom);
    }
}
