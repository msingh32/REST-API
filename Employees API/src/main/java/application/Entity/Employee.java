package application.Entity;

import java.util.UUID;

public class Employee {
    private String id;
    private String name;
    private String email;
    private int salary;

    public Employee() {
        this.id= UUID.randomUUID().toString();

    }

    public Employee(String name, String email, int salary) {
        this.id= UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


}

