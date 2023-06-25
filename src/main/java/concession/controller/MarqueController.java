package concession.controller;

import concession.controller.dto.MarqueDTO;
import concession.source.MarqueSource;
import concession.source.model.Marque;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/marque")
public class MarqueController {

    @Inject
    MarqueSource source;

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MarqueDTO> getAll()
    {
        List<MarqueDTO> marqueDTOList = new ArrayList<>();
        List<Marque> marqueList = source.getAll();

        for (Marque marque : marqueList)
        {
            marqueDTOList.add(convertToMarqueDTO(marque));
        }

        return marqueDTOList;
    }

    @GET
    @Path("/get/{nom}")
    @Produces(MediaType.APPLICATION_JSON)
    public MarqueDTO getOne(String nom)
    {
        try
        {
            return convertToMarqueDTO(source.getOne(nom));
        }
        catch (NullPointerException exception)
        {
            throw new NotFoundException("No Marque found");
        }
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(MarqueDTO marqueDTO)
    {
        Marque marque = convertToMarque(marqueDTO);

        if (source.addOne(marque)){
            return Response.status(201).build();
        }

        return Response.status(409).build();
    }

    @PUT
    @Path("/update/{nom}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOne(MarqueDTO marqueDTO, String nom)
    {
        try
        {
            Marque newMarque = convertToMarque(marqueDTO);
            Marque oldMarque = source.getOne(nom);

            if (source.updateOne(oldMarque, newMarque))
            {
                return Response.status(204).build();
            }

            return Response.status(409).build();
        }
        catch (NullPointerException exception)
        {
            throw new NotFoundException("No Marque found");
        }
    }

    @DELETE
    @Path("/delete/{nom}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOne(String nom)
    {
        if (source.deleteOne(nom))
        {
            return Response.status(204).build();
        }

        return Response.status(409).build();
    }

    private MarqueDTO convertToMarqueDTO(Marque marque)
    {
        return new MarqueDTO(
                marque.getNom(),
                marque.getAnneeCreation(),
                marque.getPays()
        );
    }

    private Marque convertToMarque(MarqueDTO marqueDTO)
    {
        return new Marque(
                marqueDTO.getNom(),
                marqueDTO.getAnneeCreation(),
                marqueDTO.getPays()
        );
    }
}
