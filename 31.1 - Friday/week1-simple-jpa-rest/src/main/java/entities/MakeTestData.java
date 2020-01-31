package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * @author Nina
 */
public class MakeTestData {

    public static void main(String[] args) {
        populate();
    }
    private static MakeTestData instance;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = getEntityManager();

    public MakeTestData() {
    }

    public static MakeTestData getMakeTestDataFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MakeTestData();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void populate() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        BankCustomer b1 = new BankCustomer("Tobias", "Tobiasen", "BC105", 56789, 12, "Hejsa");
        BankCustomer b2 = new BankCustomer("Niels", "Nielsen", "BC123", 500, 30, "Hola");
        BankCustomer b3 = new BankCustomer("Andreas", "Andreassen", "BC404", -404, 404, "NEJ");
        BankCustomer b4 = new BankCustomer("Caroline", "Carolinesen", "BC835", 20394, 56, "Goddag");
        em.getTransaction().begin();
        em.persist(b1);
        em.persist(b2);
        em.persist(b3);
        em.persist(b4);
        em.getTransaction().commit();
    }

}
