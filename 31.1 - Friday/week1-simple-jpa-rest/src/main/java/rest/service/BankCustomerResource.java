package rest.service;

import com.google.gson.Gson;
import dto.BankCustomerDTO;
import entities.BankCustomer;
import entities.MakeTestData;
import static entities.MakeTestData.getMakeTestDataFacade;
import facades.BankCustomerFacade;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("bankcustomer")
public class BankCustomerResource {

    //NOTE: Change Persistence unit name according to your setup
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
//    BankCustomerFacade facade = BankCustomerFacade.getBankCustomerFacade(emf);

    @GET
    //@Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "asdadas";
    }

    @GET
    @Path("/id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCustomerByID(@PathParam("id") int id) {
        BankCustomerFacade bcf = new BankCustomerFacade();
        BankCustomerDTO bc = bcf.getCustomerById(2);
        return new Gson().toJson(bc);
    }

    @GET
    @Path("getAll")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        BankCustomerFacade bcf = new BankCustomerFacade();
        List<BankCustomer> bcl = bcf.getAllBankCustomers();
        return new Gson().toJson(bcl);
    }

    @GET
    @Path("/populate")
    public String populate() {
        MakeTestData mtd = getMakeTestDataFacade(Persistence.createEntityManagerFactory("pu"));
        mtd.populate();
        String msg = "Updated DB";
        return new Gson().toJson(msg);
    }

}
