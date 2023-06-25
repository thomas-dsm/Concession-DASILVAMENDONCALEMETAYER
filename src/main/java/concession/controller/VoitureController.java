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

import java.util.ArrayList;
import java.util.List;

@Path("/voiture")
public class VoitureController {

    @Inject
    VoitureSource source;

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VoitureDTO> getAll()
    {
        List<VoitureDTO> voitureDTOList = new ArrayList<>();
        List<Voiture> voitureList = source.getAll();

        for (Voiture voiture : voitureList)
        {
            voitureDTOList.add(convertToVoitureDTO(voiture));
        }

        return voitureDTOList;
    }

    @GET
    @Path("/get/{immat}")
    @Produces(MediaType.APPLICATION_JSON)
    public VoitureDTO getOne(String immat)
    {
        try
        {
            return convertToVoitureDTO(source.getOne(immat));
        }
        catch (NullPointerException exception)
        {
            throw new NotFoundException("No voiture found");
        }
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(VoitureDTO voitureDTO)
    {
        Voiture voiture = convertToVoiture(voitureDTO);

        if (source.addOne(voiture)){
            return Response.status(201).build();
        }

        return Response.status(409).build();
    }

    @PUT
    @Path("/update/{immat}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOne(VoitureDTO voitureDTO, String immat)
    {
        Voiture newVoiture = convertToVoiture(voitureDTO);
        Voiture oldVoiture = source.getOne(immat);

        if (source.updateOne(oldVoiture, newVoiture))
        {
            return Response.status(204).build();
        }

        return Response.status(409).build();
    }

    @DELETE
    @Path("/delete/{immat}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOne(String immat) {

        if (source.deleteOne(immat))
        {
            return Response.status(204).build();
        }

        return Response.status(409).build();
    }

    private VoitureDTO convertToVoitureDTO(Voiture voiture){

        return new VoitureDTO(
                voiture.getMarqueId(),
                voiture.getImmat(),
                voiture.getDateImmat(),
                voiture.getPrix(),
                voiture.getType(),
                convertToCaracteristiquesDTO(voiture),
                voiture.getCouleur()
        );
    }

    private CaracteristiqueDTO convertToCaracteristiquesDTO(Voiture voiture){

        return new CaracteristiqueDTO(
                voiture.getCaracteristiques().getPuissance(),
                voiture.getCaracteristiques().getPoids(),
                voiture.getCaracteristiques().getLongueur(),
                voiture.getCaracteristiques().getLargeur(),
                voiture.getCaracteristiques().getCarburant()
        );
    }

    private Caracteristique convertToCaracteristiques(CaracteristiqueDTO caracteristiqueDTO){

        return new Caracteristique(
                caracteristiqueDTO.getPuissance(),
                caracteristiqueDTO.getPoids(),
                caracteristiqueDTO.getLongueur(),
                caracteristiqueDTO.getLargeur(),
                caracteristiqueDTO.getCarburant()
        );
    }

    private Voiture convertToVoiture(VoitureDTO voitureDTO){

        return new Voiture(
                voitureDTO.getMarqueId(),
                voitureDTO.getImmat(),
                voitureDTO.getDateImmat(),
                voitureDTO.getPrix(),
                voitureDTO.getType(),
                convertToCaracteristiques(voitureDTO.getCaracteristiques()),
                voitureDTO.getCouleur()
        );
    }
}
