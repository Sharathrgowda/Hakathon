package com.excelsoft.hackathon.employeemanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelsoft.hackathon.employeemanagement.exception.UserNotFoundException;
import com.excelsoft.hackathon.employeemanagement.modelrequest.AuthenticationRequest;
import com.excelsoft.hackathon.employeemanagement.modelrequest.RegisterRequest;
import com.excelsoft.hackathon.employeemanagement.modelresponce.AuthenticationResponse;
import com.excelsoft.hackathon.employeemanagement.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

	private final AuthenticationService service;

	@Operation(summary = "This is to Register the employee")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee registred successfully"),
			@ApiResponse(responseCode = "400", description = "Employee not registred") })
	@PostMapping("/Employeeregister")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(service.register(request));
	}

	@Operation(summary = "Employee login")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Logged in succefully"),
			@ApiResponse(responseCode = "400", description = "Invalid credintales") })
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		try {
			return ResponseEntity.ok(service.authenticate(request));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return  ResponseEntity.ok(new AuthenticationResponse(null, "Invalid credentials", HttpStatus.BAD_REQUEST));
		}
	}

}
