package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AuctionDTO;
import dtos.OwnerDTO;
import entities.Owner;
import facades.FacadeExample;
import facades.OwnerFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Path("owner")
public class OwnerRessource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final OwnerFacade FACADE =  OwnerFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<OwnerDTO> rns = FACADE.getAll();
        return Response.ok().entity(GSON.toJson(rns)).build();
    }
    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed("admin")
    public Response updateOwner(@PathParam("id") Long id, String a) {
        OwnerDTO ownerDto = GSON.fromJson(a, OwnerDTO.class);
        ownerDto.setId(id);
        OwnerDTO result = FACADE.updateOwner(ownerDto);
        return Response.ok().entity(GSON.toJson(result)).build();
    }

}

