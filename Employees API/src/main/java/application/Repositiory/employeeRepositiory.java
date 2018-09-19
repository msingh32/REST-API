package application.Repositiory;

import application.Entity.Employee;
import java.util.List;

public interface employeeRepositiory {
    List<Employee> findAll();
    Employee findOne(String id);
   
}
