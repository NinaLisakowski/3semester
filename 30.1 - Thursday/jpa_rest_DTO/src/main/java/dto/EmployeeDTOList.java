package dto;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDTOList {
    List<EmployeeDTO> empDTOs;
    
    public EmployeeDTOList (List<Employee> e) {
        List<EmployeeDTO> emps = new ArrayList();
        for (Employee employee : e) {
            emps.add(new EmployeeDTO(employee));
        }
    }
    
    public List<EmployeeDTO> getEmpDTOs() {
        return empDTOs;
    }
}