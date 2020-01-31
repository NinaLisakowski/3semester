package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Nina
 */
@Path("animal")
public class AnimalResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Hello from my first web service";
    }
    
    @GET
    @Path("/address/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonID(@PathParam("id") int id) {
        return "{\"msg\":\"it works with id: " + id + "\"}";
    }

    /**
     * PUT method for updating or creating an instance of AnimalResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandom() {
        List<Animal> list = createAnimalList();
        Random rnd = new Random();
        Object animal = list.get(rnd.nextInt(list.size()));
        return new Gson().toJson(animal);
    }
    
    public List<Animal> createAnimalList() {
        List<Animal> list = new ArrayList();
        list.add(new Animal("Duck",2017,"Quack"));
        list.add(new Animal("Unicorn",2013,"glitter"));
        list.add(new Animal("Quokka",2005,":)"));
        list.add(new Animal("Chicken",1999,"Bok"));
        return list;
    }
    
    
    
    
}
