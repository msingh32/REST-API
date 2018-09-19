package application.Controller;

import java.util.List;
import java.util.ArrayList;
import application.Entity.Employee;
import application.Service.ServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value ="employees")
public class employeeController {
    @Autowired
    private ServiceImp serv;
    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> findAll()
    {
    return serv.findAll();
    }
    @RequestMapping(method = RequestMethod.GET,value = "{id}")
    public Employee findOne(@PathVariable("id")String id)
    {
        return serv.findOne(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    public Employee Create(@RequestBody Employee employee)
    {
        return employee;
    }
    @RequestMapping(method = RequestMethod.PUT,value = "{id}")
    public Employee update(@PathVariable("id")String id,@RequestBody Employee employee)
    {
        return employee;
    }
    @RequestMapping(method = RequestMethod.DELETE,value = "{id}")
    public void delete(@PathVariable("id")String id)
    {

    }
}
