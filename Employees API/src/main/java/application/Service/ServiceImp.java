package application.Service;

import application.Entity.Employee;
import application.Repositiory.EmpServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import application.Exception.EmployeeNotFoundException;

@Service
public class ServiceImp implements employeeService {
    @Autowired
    EmpServiceImp emp;
    @Override
    public List<Employee> findAll()
    {
    return emp.findAll();
    }
   @Override
    public Employee findOne(String id)
    {
        Employee employee=emp.findOne(id);
        if(employee==null)
        {
            throw new EmployeeNotFoundException("Employee with id:"+id+"Not Found");
        }
        else
        {
            return  employee;
        }
    }
    @Override
    public Employee create(Employee employee)
    {
        return null;
    }
    @Override
    public Employee update(String id, String name)
    {
        return null;
    }
    @Override
    public void delete(String id)
    {

    }
}
