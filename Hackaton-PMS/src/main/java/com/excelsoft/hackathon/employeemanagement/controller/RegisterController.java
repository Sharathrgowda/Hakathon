package com.excelsoft.hackathon.employeemanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelsoft.hackathon.employeemanagement.exception.UserNotFoundException;
import com.excelsoft.hackathon.employeemanagement.modelrequest.EmployeeBulkRequest;
import com.excelsoft.hackathon.employeemanagement.modelrequest.EmployeeDetailesRequest;
import com.excelsoft.hackathon.employeemanagement.modelrequest.ProjectBulkInsert;
import com.excelsoft.hackathon.employeemanagement.modelrequest.ProjectDetailRequest;
import com.excelsoft.hackathon.employeemanagement.modelresponce.Response;
import com.excelsoft.hackathon.employeemanagement.service.EmployeeDetailesService;
import com.excelsoft.hackathon.employeemanagement.service.ProjectDetailesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/register-Controller")
@RequiredArgsConstructor
@CrossOrigin
public class RegisterController {

	private final EmployeeDetailesService employeeDetailesService;
	private final ProjectDetailesService projectDetailsService;
	
	
	@Operation(summary = "This is to add the employee one by one")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee added successfully"),
			@ApiResponse(responseCode = "400", description = "Data not added") })
	@PostMapping("/employeeRegister")
	public Response<?> empRegister(@RequestBody EmployeeDetailesRequest emDetailesRequest) {
		try {
			if (employeeDetailesService.register(emDetailesRequest) != null) {
				return Response.builder().statusCode("200").discription("added succefully").build();

			} else {
				return Response.builder().statusCode("400").discription("Data not added").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@Operation(summary = "This is to add the project to the employee")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Adding the project to the employee"),
			@ApiResponse(responseCode = "400", description = "Data not added") })
	@PostMapping("/AddingProject")
	public Response<?> projectDetailRegister(@RequestBody ProjectDetailRequest projectdetailReq) {
		try {
			projectDetailsService.projectResister(projectdetailReq);
			return Response.builder().statusCode("200").discription("Project added succefully").build();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return Response.builder().statusCode("400").discription("Employee code not found for the data given")
					.build();
		}

	}
	
	@Operation(summary = "This is to add the employes data in bulk")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employes data added successfully"),
			@ApiResponse(responseCode = "400", description = "Data not added") })
	@PostMapping("/employeeBulk")
	public ResponseEntity<Void> saveEmployeesInBulk(@RequestBody EmployeeBulkRequest employeeBulkRequest) {
		employeeDetailesService.saveEmployeesInBulk(employeeBulkRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Operation(summary = "This is to add the bulk projects to the employes based on the employee code")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Projects added to the employee"),
			@ApiResponse(responseCode = "400", description = "Projects not added") })
	@PostMapping("/projectBulk")
	public ResponseEntity<Void> saveProjectsInBulk(@RequestBody List<ProjectBulkInsert> projectBulkRequest)
			throws Exception {
		projectDetailsService.saveProjectsInBulk(projectBulkRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
