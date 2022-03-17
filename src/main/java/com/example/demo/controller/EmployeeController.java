package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.IEmployeeService;

@RestController
@RequestMapping("/employee/api")
public class EmployeeController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IEmployeeService employeeService;

	@GetMapping("/getData")
	public String getData(@RequestParam String name) {
		return "Hello :" + name.toUpperCase();
	}

	@PostMapping("/createEmployee")
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
		System.out.println("EmployeeController : createEmployee method");
		Employee employeeRequest = modelMapper.map(employeeDTO, Employee.class);
		// Employee employeeRequest = convertDtoToEntity(employeeDTO);
		Employee employee = employeeService.createEmployee(employeeRequest);
		EmployeeDTO employeeReponse = modelMapper.map(employee, EmployeeDTO.class);
		// EmployeeDTO employeeReponse =convertEntityToDto(employee);
		return new ResponseEntity<EmployeeDTO>(employeeReponse, HttpStatus.CREATED);
	}

	@GetMapping("/getEmployees")
	public List<EmployeeDTO> getEmployees() {
		System.out.println("EmployeeController : getEmployees method");
		List<Employee> employees = employeeService.getEmployees();
		return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/getEmployee")
	public EmployeeDTO getEmployee(@RequestParam Integer id) {
		System.out.println("EmployeeController : getEmployee method");
		Employee employee = employeeService.getEmployee(id);
		EmployeeDTO employeeReponse = modelMapper.map(employee, EmployeeDTO.class);
		return employeeReponse;
	}

	@PutMapping("/updateEmployee")
	public ResponseEntity<EmployeeDTO> updateEmployee(@RequestParam Integer id, @RequestBody EmployeeDTO employeeDTO) {
		System.out.println("EmployeeController : updateEmployee method");
		Employee employeeRequest = modelMapper.map(employeeDTO, Employee.class);
		Employee updatedEmployee = employeeService.updateEmployee(id,employeeRequest);
		EmployeeDTO employeeReponse = modelMapper.map(updatedEmployee, EmployeeDTO.class);
		return new ResponseEntity<EmployeeDTO>(employeeReponse, HttpStatus.OK);
	}

	@DeleteMapping("/deleteEmployee")
	public Map<String, Boolean> deleteEmployee(@RequestParam Integer id) {
		System.out.println("EmployeeController : deleteEmployee method");
		Employee employee = employeeService.getEmployee(id);
		employeeService.deleteEmployee(employee.getId());
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted.", Boolean.TRUE);
		return map;
	}

	/*
	 * private static EmployeeDTO convertEntityToDto(Employee employee) {
	 * EmployeeDTO employeeDTO = new EmployeeDTO();
	 * employeeDTO.setId(employee.getId()); employeeDTO.setName(employee.getName());
	 * employeeDTO.setAge(employee.getAge());
	 * employeeDTO.setGender(employee.getGender());
	 * employeeDTO.setAddress(employee.getAddress());
	 * employeeDTO.setSalary(employee.getSalary()); return employeeDTO; }
	 * 
	 * private static Employee convertDtoToEntity(EmployeeDTO employeeDTO) {
	 * Employee employee = new Employee(); employee.setId(employeeDTO.getId());
	 * employee.setName(employeeDTO.getName());
	 * employee.setAge(employeeDTO.getAge());
	 * employee.setGender(employeeDTO.getGender());
	 * employee.setSalary(employeeDTO.getSalary());
	 * employee.setAddress(employeeDTO.getAddress()); return employee;
	 * 
	 * }
	 */

}
