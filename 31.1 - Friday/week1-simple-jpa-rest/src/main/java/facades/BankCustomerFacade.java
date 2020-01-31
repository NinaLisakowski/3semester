package facades;

import dto.BankCustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = getEntityManager();

    public BankCustomerFacade() {
    }

    /*
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomerFacade getBankCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public BankCustomerDTO getCustomerById(int id) {
        BankCustomer bc = em.find(BankCustomer.class, id);
        return new BankCustomerDTO(bc);
    }

    public List<BankCustomerDTO> getCustomersByName(String fullName) {
        String[] sArray = fullName.split(" ");
        String fName = sArray[0].trim();
        String lName = sArray[1].trim();
        TypedQuery<BankCustomer> tq = em.createQuery("SELECT b FROM BankCustomer b where b.firstName = :firstName AND b.lastName = :lastName", BankCustomer.class);
        tq.setParameter("firstName", fName);
        tq.setParameter("lastName", lName);
        List<BankCustomer> customers = tq.getResultList();
        List<BankCustomerDTO> customersDTO = new ArrayList();
        for (BankCustomer bc : customers) {
            BankCustomerDTO bcDTO = new BankCustomerDTO(bc);
            customersDTO.add(bcDTO);
        }
        return customersDTO;
    }

    public List<BankCustomer> getAllBankCustomers() {
        TypedQuery q = em.createQuery("SELECT b FROM BankCustomer b", BankCustomer.class);
        return q.getResultList();
    }

    public BankCustomer addCustomer(BankCustomer cust) {
        em.getTransaction().begin();
        em.persist(cust);
        em.getTransaction().commit();
        return cust;
    }

    public static void main(String[] args) {
        BankCustomerFacade bcf = new BankCustomerFacade();
        bcf.addCustomer(new BankCustomer("Tobias", "Tobiasen", "BC105", 56789, 12, "Hejsa"));
        bcf.addCustomer(new BankCustomer("Niels", "Nielsen", "BC123", 500, 30, "Hola"));
        bcf.addCustomer(new BankCustomer("Andreas", "Andreassen", "BC404", -404, 404, "NEJ"));
        bcf.addCustomer(new BankCustomer("Caroline", "Carolinesen", "BC835", 20394, 56, "Goddag"));
        System.out.println(bcf.getCustomerById(2));
        System.out.println(bcf.getAllBankCustomers());
        System.out.println(bcf.getCustomersByName("Tobias Tobiasen"));
    }

}
