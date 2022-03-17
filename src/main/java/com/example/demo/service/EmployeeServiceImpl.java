package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeIdNotFound;
import com.example.demo.repository.IEmployeeRepo;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepo employeeRepo;

	@Override
	public Employee createEmployee(Employee employee) {
		System.out.println("EmployeeServiceImpl : createEmployee method");
		return employeeRepo.save(employee);
	}

	@Override
	public List<Employee> getEmployees() {
		System.out.println("EmployeeServiceImpl : getEmployees method");
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployee(Integer id) {
		System.out.println("EmployeeServiceImpl : getEmployee method");
	Employee employee=	employeeRepo.findById(id).orElseThrow(()-> new EmployeeIdNotFound("Employee id not found :"+id));
	return employee;
		/*Optional<Employee> result= employeeRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			   throw new EmployeeIdNotFound("Employee id not found :"+id);
		}*/
		
	}

	@Override
	public Employee updateEmployee(Integer id, Employee employee) {
		System.out.println("EmployeeServiceImpl : updateEmployee method");
		Employee employeeObject =	employeeRepo.findById(id).orElseThrow(()-> new EmployeeIdNotFound("Employee id not found :"+id));
		employeeObject.setName(employee.getName());
		employeeObject.setAge(employee.getAge());
		employeeObject.setGender(employee.getGender());
		employeeObject.setSalary(employee.getSalary());
		employeeObject.setAddress(employee.getAddress());
		 return	employeeRepo.save(employeeObject);
		/*Optional<Employee> result = employeeRepo.findById(id);
		if(result.isPresent()) {
			Employee employeeObject= result.get();
			employeeObject.setName(employee.getName());
			employeeObject.setAge(employee.getAge());
			employeeObject.setGender(employee.getGender());
			employeeObject.setSalary(employee.getSalary());
			employeeObject.setAddress(employee.getAddress());
			 return	employeeRepo.save(employeeObject);
		}
		else {
			   throw new EmployeeIdNotFound("Employee id not found :"+id);
		}*/
	}

	@Override
	public void deleteEmployee(Integer id) {
		System.out.println("EmployeeServiceImpl : deleteEmployee method");
		employeeRepo.deleteById(id);;
	}

}
