package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import facades.EmployeeFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employee")
public class EmployeeResource {

    //NOTE: Change Persistence unit name according to your setup
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);

//    private final boolean setup = addEmployees();
//    private boolean addEmployees(){
//        facade.createEmployee("Ole Bent", "Kummevej 4", 100);
//        facade.createEmployee("Kagemand Hallo", "Yesyes 25", 550);
//        facade.createEmployee("Lone Holland", "Motorvej 96", 968);
//        facade.createEmployee("Kent Borgesen", "Lyngbyvej 27", 100);
//        
//        return true;
//    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("id") int id) {
        EmployeeFacade ef = new EmployeeFacade();
        ef.createEmployee("Andreas", "Andreasvej", 10);
        EmployeeDTO e = ef.getEmployeeById(id);
        return new Gson().toJson(e);
    }

    @GET
    @Path("/all")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getAll() {
        EmployeeFacade ef = new EmployeeFacade();
        ef.createEmployee("Andreas", "Andreasvej", 10);
        List<EmployeeDTO> employees = ef.getAllEmployees();
        return new Gson().toJson(employees);
    }

    @GET
    @Path("/highestpaid")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getEmployeesWithHighestSalary(@PathParam("salary") int salary) {
        EmployeeFacade ef = new EmployeeFacade();
        ef.createEmployee("Andreas", "Andreasvej", 10);
        ef.createEmployee("Andreas", "Andreasvej", 122);
        List<EmployeeDTO> employees = ef.getEmployeesWithHighestSalary();
        return new Gson().toJson(employees);
    }

    
    @GET
    @Path("/name/{name}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getEmployeesByName(@PathParam("name") String name) {
        EmployeeFacade ef = new EmployeeFacade();
        ef.createEmployee("Andreas", "Andreasvej", 10);
        ef.createEmployee("Andreas", "Andreasvej", 11);
        List<EmployeeDTO> emp = ef.getEmployeesByName(name);
        return new Gson().toJson(emp);
    }
    
}
