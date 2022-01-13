package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BoatDTO;
import dtos.OwnerDTO;
import facades.BoatFacade;
import facades.OwnerFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("boat")
public class BoatRessource {


        private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
        private static final BoatFacade FACADE =  BoatFacade.getFacadeExample(EMF);
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
        List<BoatDTO> rns = FACADE.getAll();
        return Response.ok().entity(GSON.toJson(rns)).build();
    }

    @GET
    @Path("getboatbyowner/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed("user")
    public Response getBoatByOwner(@PathParam("name") String name) {
        List<BoatDTO> rn = FACADE.getBoatByOwner(name);
        return Response.ok().entity(GSON.toJson(rn)).build();
    }

    @POST
    @Path("createboat")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes ({MediaType.APPLICATION_JSON})
    @RolesAllowed("user")
    public Response createBoat(BoatDTO boatDTO) {
           boatDTO = FACADE.create(boatDTO);
           return Response.ok().entity(GSON.toJson(boatDTO)).build();

    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed("user")
    public Response updateBoat(@PathParam("id") Long id, String a) {
        BoatDTO boatDto = GSON.fromJson(a, BoatDTO.class);
        boatDto.setId(id);
        BoatDTO result = FACADE.updateBoat(boatDto);
        return Response.ok().entity(GSON.toJson(result)).build();
    }


}
