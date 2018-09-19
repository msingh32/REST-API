package application.Service;

import application.Entity.Employee;
import java.util.List;

public interface employeeService {
    List<Employee> findAll();
    Employee findOne(String id);
    Employee create(Employee emp);
    Employee update(String id,String name);
    void delete(String id);
}
