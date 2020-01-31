package rest;

import com.google.gson.Gson;
import dbfacade.CustomerFacade;
import entity.Customer;
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
@Path("customer")
public class CustomerResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CustomerResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CustomerResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getCustomerById(@PathParam("id") int id) {
        CustomerFacade cf = new CustomerFacade();
        cf.addCustomer("Henrik", "Henriksen");
        Object c = cf.findByID(id);
        return new Gson().toJson(c);
    }
    
    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    public String allCustomers() {
        CustomerFacade cf = new CustomerFacade();
        cf.addCustomer("Henrik", "Henriksen");
        cf.addCustomer("Bente", "Bentsen");
        List<Customer> cl = cf.allCustomers();
        return new Gson().toJson(cl);
    }
    
    @GET
    @Path("/random")
    @Consumes(MediaType.APPLICATION_JSON) 
    public String randomCustomer() {
        Random rnd = new Random();
        List<Customer> cl = createCustomerList();
        Object cus = cl.get(rnd.nextInt(cl.size()));
        return new Gson().toJson(cus);
    }

    private List<Customer> createCustomerList() {
        List<Customer> list = new ArrayList();
        list.add(new Customer("Allan", "Allansen"));
        list.add(new Customer("Karl", "Karlsen"));
        list.add(new Customer("Hans", "Hansen"));
        list.add(new Customer("Gunnar", "Gunnarsen"));
        return list;
    }
}
