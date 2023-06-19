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
            entretienDTOList.add(convertToEntretienDTO(entretien));
        }

        return entretienDTOList;
    }

    @POST
    @Path("/add/{immat}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOneByVoiture(EntretienDTO entretienDTO, String immat)
    {
        if (source.addOneByVoiture(convertToEntretien(entretienDTO), immat))
        {
            return Response.status(204).build();
        }

        return Response.status(409).build();
    }

    private EntretienDTO convertToEntretienDTO(Entretien entretien)
    {
        return new EntretienDTO(
                entretien.getVoitureId(),
                entretien.getDate(),
                entretien.getDescription(),
                entretien.getGarage()
        );
    }

    private Entretien convertToEntretien(EntretienDTO entretienDTO)
    {
        return new Entretien(
                entretienDTO.getVoitureId(),
                entretienDTO.getDate(),
                entretienDTO.getDescription(),
                entretienDTO.getGarage()
        );
    }
}
