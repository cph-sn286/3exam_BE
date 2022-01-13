package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AuctionDTO;
import dtos.BoatDTO;
import entities.Auction;
import facades.AuctionFacade;
import facades.FacadeExample;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Path("auction")

public class AuctionRessource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final AuctionFacade FACADE =  AuctionFacade.getFacadeExample(EMF);
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
        List<AuctionDTO> rns = FACADE.getAll();
        return Response.ok().entity(GSON.toJson(rns)).build();
    }

    @POST
    @Path("createauction")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed("admin")
    public Response createAuction(AuctionDTO auctionDTO) {
        auctionDTO = FACADE.createAuction(auctionDTO);
        return Response.ok().entity(GSON.toJson(auctionDTO)).build();
    }
    @DELETE
    @Path("deleteauction/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed("admin")
    public Response deleteAuction(@PathParam("id") int id) {
        FACADE.deleteAuction(id);
        return Response.ok().entity(GSON.toJson(id)).build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
   @RolesAllowed("admin")
    public Response updateAuction(@PathParam("id") Long id, String a) {
        AuctionDTO auctionDto = GSON.fromJson(a, AuctionDTO.class);
        auctionDto.setId(id);
        AuctionDTO result = FACADE.updateAuction(auctionDto);
        return Response.ok().entity(GSON.toJson(result)).build();
    }


}