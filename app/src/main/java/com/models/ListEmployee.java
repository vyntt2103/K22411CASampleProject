package com.models;

import java.util.ArrayList;
import java.util.List;

public class ListEmployee {
    private List<Employee> employees;

    public ListEmployee()
    {
        employees=new ArrayList<>();
    }

    public List<Employee> getEmployees()
    {
        return employees;
    }

    public void generate_sample_dataset()
    {
        Employee e1=new Employee();
        e1.setName("John");
        e1.setEmail("john@gmail.com");
        e1.setUsername("john");
        e1.setPassword("123");
        employees.add(e1);

        Employee e2=new Employee();
        e2.setName("Jack");
        e2.setEmail("jack@gmail.com");
        e2.setUsername("j97");
        e2.setPassword("345");
        employees.add(e2);

        Employee e3=new Employee();
        e3.setName("Jenny");
        e3.setEmail("jenny@gmail.com");
        e3.setUsername("jennie");
        e3.setPassword("678");
        employees.add(e3);
    }
}
