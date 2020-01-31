package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * @author Nina
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();

    public CustomerFacade() {
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        //Add customers
        Customer c1 = facade.addCustomer("Anders", "Andersen");
        Customer c2 = facade.addCustomer("Jens", "Jensen");
        //Find customer based on ID
        System.out.println("Customer1 with id 1: " + facade.findByID(1));
        //Find customer based on last name
        System.out.println("Customer2 with last name Andersen: " + facade.findCustomerByLastName("Andersen"));
        //Find number of total customers
        System.out.println("Number of customers: " + facade.getNumberOfCustomers());
        //Find all customers
        System.out.println("All customers: " + facade.allCustomers());
    }

    private static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Customer findByID(int id) {
        Customer customer = em.find(Customer.class, id);
        return customer;
    }

    public List<Customer> findCustomerByLastName(String lastName) {
        TypedQuery<Customer> tp = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :lastName", Customer.class);
        tp.setParameter("lastName", lastName);
        return tp.getResultList();
    }

    public int getNumberOfCustomers() {
        TypedQuery tq = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return  tq.getResultList().size();
    }

    public List<Customer> allCustomers() {
        TypedQuery<Customer> query
                = em.createQuery("SELECT c from Customer c", Customer.class);
        return query.getResultList();
    }

    public Customer addCustomer(String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName);
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        return customer;
    }

}
