package concession.controller;

import concession.controller.dto.EntretienDTO;
import concession.controller.dto.VoitureDTO;
import concession.source.EntretienSource;
import concession.source.model.Entretien;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Path("/entretien")
public class EntretienController {

    @Inject
    EntretienSource source;

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EntretienDTO> getAllByVoiture(VoitureDTO voiture)
    {
        List<EntretienDTO> entretienDTOList = new ArrayList<>();

        for (Entretien entretien : source.getAllByVoiture(voiture.getImmat()))
        {
            entretienDTOList.add(convertEntretien(entretien));
        }

        return entretienDTOList;
    }

    @POST
    @Path("/add/{immat}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOneByVoiture(EntretienDTO entretien, String immat)
    {
        Document entretienDocument = new Document();

        entretienDocument.append("date", entretien.getDate());
        entretienDocument.append("description", entretien.getDescription());
        entretienDocument.append("garage", entretien.getGarage());

        return source.addOneByVoiture(entretienDocument, immat);
    }

    private EntretienDTO convertEntretien(Entretien entretien)
    {
        return new EntretienDTO(
                entretien.getVoitureId(),
                entretien.getDate(),
                entretien.getDescription(),
                entretien.getGarage()
        );
    }
}
