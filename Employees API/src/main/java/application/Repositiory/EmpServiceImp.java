package application.Repositiory;

import application.Entity.Employee;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmpServiceImp implements employeeRepositiory
{
    @Override
    public List<Employee> findAll()
    {
        List<Employee> employees=new ArrayList<>();
        employees.add(new Employee("mili","mili@stfc.co.in",1000));
        employees.add(new Employee("megha","megha@stfc.co.in",2000));
        employees.add(new Employee("mahi","mahi@stfc.co.in",5000));
        return employees;
    }
   @Override
    public Employee findOne(String id)
    {
       // return new Employee("mili","mili@stfc.co.in",1000);
        return null;

    }

}
