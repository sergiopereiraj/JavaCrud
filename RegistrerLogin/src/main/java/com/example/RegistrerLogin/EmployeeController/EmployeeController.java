package com.example.RegistrerLogin.EmployeeController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegistrerLogin.Dto.EmployeeDTO;
import com.example.RegistrerLogin.Dto.LoginDTO;
import com.example.RegistrerLogin.Service.EmployeeService;
import com.example.RegistrerLogin.response.LoginResponse;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping(path = "/save")
	public String saveEmployee(@RequestBody EmployeeDTO employeeDTO) 
	{
		String id = employeeService.addEmployee(employeeDTO);
		return id;
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO){
		
		LoginResponse loginResponse = employeeService.loginEmployee(loginDTO);
		return ResponseEntity.ok(loginResponse);
		
	}
	
	

}
