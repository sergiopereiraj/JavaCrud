package com.example.RegistrerLogin.Service;

import org.springframework.core.log.LogMessage;

import com.example.RegistrerLogin.Dto.EmployeeDTO;
import com.example.RegistrerLogin.Dto.LoginDTO;
import com.example.RegistrerLogin.response.LoginResponse;


public interface EmployeeService {

	String addEmployee(EmployeeDTO employeeDTO);

	LoginResponse loginEmployee(LoginDTO loginDTO);
	
}
