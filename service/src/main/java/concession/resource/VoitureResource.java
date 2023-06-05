package concession.resource;

import concession.service.VoitureService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
}
