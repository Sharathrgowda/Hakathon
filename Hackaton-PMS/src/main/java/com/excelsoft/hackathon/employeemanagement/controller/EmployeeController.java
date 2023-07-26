package com.excelsoft.hackathon.employeemanagement.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.excelsoft.hackathon.employeemanagement.entity.User;
import com.excelsoft.hackathon.employeemanagement.exception.DuplicateDataExcetion;
import com.excelsoft.hackathon.employeemanagement.exception.UserNotFoundException;
import com.excelsoft.hackathon.employeemanagement.modelrequest.EmployeeDetailesRequest;
import com.excelsoft.hackathon.employeemanagement.modelrequest.ProjectDetailRequest;
import com.excelsoft.hackathon.employeemanagement.modelrequest.SkillRequest;
import com.excelsoft.hackathon.employeemanagement.modelrequest.UserRequest;
import com.excelsoft.hackathon.employeemanagement.modelresponce.Response;
import com.excelsoft.hackathon.employeemanagement.service.EmployeeDetailesService;
import com.excelsoft.hackathon.employeemanagement.service.ProjectDetailesService;
import com.excelsoft.hackathon.employeemanagement.service.SkillsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employee-controller")
@RequiredArgsConstructor
@CrossOrigin
public class EmployeeController {

	private final EmployeeDetailesService employeeDetailesService;
	private final ProjectDetailesService projectDetailsService;
	private final SkillsService skillsService;

	@Operation(summary = "This is to get all the employee details with theie skilss and project detailes")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Data fetched successfully"),
			@ApiResponse(responseCode = "400", description = "Data not found") })
	@GetMapping("/getEmployeeDetailes")
	public ResponseEntity<?> getEmployeeList1() {
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		try {
			return ResponseEntity.ok(employeeDetailesService.getEmployeeProfile(principal.getEmail()));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("NO data found ");
		}
	}

	@Operation(summary = "This is to add the skills based on the employee code")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Skill added succefully"),
			@ApiResponse(responseCode = "400", description = "Please enter the correct employee codew") })
	@PostMapping("/AddingnewSkills")

	public Response<?> newSkillInsert(@RequestBody SkillRequest skillRequest) throws Exception, DuplicateDataExcetion {
		try {

			skillsService.addSkills(skillRequest);
			return Response.builder().statusCode("200").discription("Skill added successfully").build();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return Response.builder().statusCode("400").discription("Employee not found for the code given").build();
		} catch (DuplicateDataExcetion e) {
			return Response.builder().statusCode("400").discription("Skill already present ").build();

		}
	}
	@Operation(summary = "This is to delete the skills based on the employee code")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Skill delete successfully"),
            @ApiResponse(responseCode = "400", description = "Please enter the correct employee codew") })
    @PostMapping("/DeleteSkills")
    public Response<?> deleteSkill(@RequestBody SkillRequest skillRequest) throws DuplicateDataExcetion{
        try {
            skillsService.deleteSkill(skillRequest);
            return Response.builder().statusCode("200").discription("Skill deleted successfully").build();
        } catch (UserNotFoundException ex) {
            return Response.builder().statusCode("400").discription("Employee not found").build();
        } catch (DuplicateDataExcetion ex) {
            return Response.builder().statusCode("400").discription("Skill not present ").build();
    }
}


	@Operation(summary = "This is to fetch the skills from the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Skills fetched successfully"),
			@ApiResponse(responseCode = "400", description = "Skills not found") })
	@GetMapping("/skills")
	public Set<String> getSkills() {

		return skillsService.getSkills();

	}

	@Operation(summary = "This is to fetch the employee data from external api and saving to database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Data fetch and saved successfully"),
			@ApiResponse(responseCode = "400", description = "Data not saved") })
	@GetMapping("/external-data")
	public Response<?> saveExternalData() {
		try {
			RestTemplate restTemplate = new RestTemplate();

			EmployeeDetailesRequest[] data = restTemplate.getForObject(
					"https://pegtestdev.excelindia.com/pms/employees.json", EmployeeDetailesRequest[].class);
			List<EmployeeDetailesRequest> dataList = Arrays.asList(data);
			employeeDetailesService.saveEmp(dataList);
			return Response.builder().statusCode("200").discription("Data added successfully").build();
		} catch (Exception e) {
			return Response.builder().statusCode("400").discription("Data not added").build();

		}
	}

	@Operation(summary = "This is to fetch the user for autogenerating the user email and password")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Data Saved successfully"),
			@ApiResponse(responseCode = "400", description = "Data not saved") })
	@GetMapping("/external-user")
	public Response<?> saveExternalDatas() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			UserRequest[] data = restTemplate.getForObject("https://pegtestdev.excelindia.com/pms/employees.json",
					UserRequest[].class);
			List<UserRequest> dataList = Arrays.asList(data);
			employeeDetailesService.savesuer(dataList);
			return Response.builder().statusCode("200").discription("Data added successfully").build();
		} catch (Exception e) {
			return Response.builder().statusCode("400").discription("Data not added").build();
		}

	}

	@Operation(summary = "This is to fetch the project details from external api and save it to database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Data Saved successfully"),
			@ApiResponse(responseCode = "400", description = "Data not saved") })
	@GetMapping("/external-api")
	public Response<?> saveProjectData() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ProjectDetailRequest[] data = restTemplate
					.getForObject("https://pegtestdev.excelindia.com/pms/projects.json", ProjectDetailRequest[].class);
			List<ProjectDetailRequest> dataList = Arrays.asList(data);
			projectDetailsService.SaveExternalData(dataList);
			return Response.builder().statusCode("200").discription("Data added successfully").build();

		} catch (Exception e) {
			return Response.builder().statusCode("400").discription("Data not added").build();

		}

	}

}
