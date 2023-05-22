package com.example.RegistrerLogin.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.RegistrerLogin.Dto.EmployeeDTO;
import com.example.RegistrerLogin.Dto.LoginDTO;
import com.example.RegistrerLogin.Entity.Employee;
import com.example.RegistrerLogin.Repo.EmployeeRepo;
import com.example.RegistrerLogin.Service.EmployeeService;
import com.example.RegistrerLogin.response.LoginResponse;

@Service
public class EmployeeIMPL implements EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public String addEmployee(EmployeeDTO employeeDTO) {
		
		Employee employee = new Employee(
				
				employeeDTO.getEmployeeid(),
				employeeDTO.getEmployeename(),
				employeeDTO.getEmail(),
				
				this.passwordEncoder.encode(employeeDTO.getPassword())
				
				);
		
		employeeRepo.save(employee);
		
		return employee.getEmployeename();
	}


	@Override
	public LoginResponse loginEmployee(LoginDTO loginDTO) {
		 String msg = "";
	        Employee employee1 = employeeRepo.findByEmail(loginDTO.getEmail());
	        if (employee1 != null) {
	            String password = loginDTO.getPassword();
	            String encodedPassword = employee1.getPassword();
	            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
	            if (isPwdRight) {
	                Optional<Employee> employee = employeeRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
	                if (employee.isPresent()) {
	                    return new LoginResponse("Login Success", true);
	                } else {
	                    return new LoginResponse("Login Failed", false);
	                }
	            } else {
	 
	                return new LoginResponse("password Not Match", false);
	            }
	        }else {
	            return new LoginResponse("Email not exits", false);
	        }
	}
}
