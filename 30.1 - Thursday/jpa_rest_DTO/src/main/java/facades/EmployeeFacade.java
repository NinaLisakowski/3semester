package facades;

import dto.EmployeeDTO;
import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = getEntityManager();

    public EmployeeFacade() {
    }
    
    /*
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EmployeeDTO getEmployeeById(int id) {
            Employee ep = em.find(Employee.class, id);
            return new EmployeeDTO(ep);
    }

    public List<EmployeeDTO> getEmployeesByName(String Name) {
            TypedQuery<EmployeeDTO> tq = em.createQuery("SELECT e name from Employee e where e.name = :Name", EmployeeDTO.class);
            tq.setParameter("Name", Name);
            return tq.getResultList();
    }

    public List<EmployeeDTO> getAllEmployees() {
            TypedQuery q = em.createQuery("SELECT e FROM Employee e", Employee.class);
            return q.getResultList();
    }
    
    public List<EmployeeDTO> getEmployeesWithHighestSalary() {
            TypedQuery q = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)", Employee.class);
            return q.getResultList();
    }

    public Employee createEmployee(String name, String address, int salary) {
        Employee ep = new Employee(name, address, salary);
            em.getTransaction().begin();
            em.persist(ep);
            em.getTransaction().commit();
            return ep;
    }
    
    public static void main (String[] args) {
        EmployeeFacade ef = new EmployeeFacade();
        ef.createEmployee("Ole Bent", "Kummevej 4", 100);
        ef.createEmployee("Kagemand Hallo", "Yesyes 25", 550);
        ef.createEmployee("Lone Holland", "Motorvej 96", 968);
        ef.createEmployee("Kent Borgesen", "Lyngbyvej 27", 100);
        System.out.println(ef.getEmployeeById(1));
        System.out.println(ef.getEmployeesByName("Ole Bent"));
        System.out.println(ef.getAllEmployees());
        System.out.println(ef.getEmployeesWithHighestSalary());
    }

}
