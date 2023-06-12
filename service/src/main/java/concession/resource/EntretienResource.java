package concession.resource;

import concession.document.Entretien;
import concession.document.Voiture;
import concession.service.EntretienService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;

@Path("/entretien")
public class EntretienResource {

    @Inject
    EntretienService service;

    @GET
    @Path("/get")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllByVoiture(Voiture voiture) {
        return service.getAllByVoiture(voiture.getImmat());
    }

    @POST
    @Path("/add/{immat}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addOneByVoiture(Entretien entretien, String immat) {
        Document entretienDocument = new Document();

        entretienDocument.append("date", entretien.getDate());
        entretienDocument.append("description", entretien.getDescription());
        entretienDocument.append("garage", entretien.getGarage());

        return service.addOneByVoiture(entretienDocument, immat);
    }
}
