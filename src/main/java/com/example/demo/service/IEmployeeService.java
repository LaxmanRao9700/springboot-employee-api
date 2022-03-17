package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Employee;

public interface IEmployeeService {

	public Employee createEmployee(Employee employee);
	
	public List<Employee> getEmployees();
	
	public Employee getEmployee(Integer id);
	
	public Employee updateEmployee(Integer id, Employee employee);
	
	public void deleteEmployee(Integer id);
}
