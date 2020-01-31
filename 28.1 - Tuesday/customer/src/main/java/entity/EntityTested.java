package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Nina
 */
public class EntityTested {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Customer customer1 = new Customer("Lars", "Larsen");
        Customer customer2 = new Customer("Hans", "Hansen");
        
        try {
            em.getTransaction().begin();
            em.persist(customer1);
            em.persist(customer2);
            em.getTransaction().commit();
            //Verify that books are managed and has been given a database id
            System.out.println(customer1.getId());
            System.out.println(customer2.getId());

        } finally {
            em.close();
        }
    }
}