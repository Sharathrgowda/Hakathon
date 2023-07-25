package com.excelsoft.hackathon.employeemanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.excelsoft.hackathon.employeemanagement.entity.EmployeeDetails;
import com.excelsoft.hackathon.employeemanagement.exception.UserNotFoundException;
import com.excelsoft.hackathon.employeemanagement.modelrequest.EmployeeDetailesRequest;
import com.excelsoft.hackathon.employeemanagement.modelresponce.EmployeeProfileResponse;
import com.excelsoft.hackathon.employeemanagement.repository.EmployeeDetailsRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeDetailesServiceTest {

	@Mock
	private EmployeeDetailsRepository employeeDeayilRepo;

	@InjectMocks
	private EmployeeDetailesService employeedeatilService;

	@Test
	public void employeeRegister() {
		EmployeeDetails details = new EmployeeDetails();
		details.setDepartment("test");
		EmployeeDetailesRequest detailesRequest = new EmployeeDetailesRequest();
		detailesRequest.setDepartment("test");
		when(employeeDeayilRepo.save(any())).thenReturn(details);
		assertEquals(details.getDepartment(), employeedeatilService.register(detailesRequest).getDepartment());
	}

	@Test
	public void getEmployeeProfile() throws UserNotFoundException {
		EmployeeDetails details = new EmployeeDetails();
		details.setEmailId("meghana@gmail.com");
		EmployeeProfileResponse detailResponse = new EmployeeProfileResponse();
		detailResponse.setEmailId("meghana@gmail.com");
		List<EmployeeDetails> listDetail = new ArrayList<EmployeeDetails>();
		listDetail.add(details);
		when(employeeDeayilRepo.findByEmailId(anyString())).thenReturn(details);
		when(employeeDeayilRepo.findByReportingEmailId(anyString())).thenReturn(listDetail);
		assertEquals(detailResponse.getEmailId(),
				employeedeatilService.getEmployeeProfile("meghana@gmail.com").getEmailId());
	}

	@Test
	public void getEmployeeProfile1() throws UserNotFoundException {
		EmployeeDetails details = new EmployeeDetails();
		details.setEmailId("meghana@gmail.com");
		details.setDesignation("Manager");
		EmployeeProfileResponse detailResponse = new EmployeeProfileResponse();
		detailResponse.setEmailId("meghana@gmail.com");
		List<EmployeeDetails> listDetail = new ArrayList<EmployeeDetails>();
		listDetail.add(details);
		when(employeeDeayilRepo.findByEmailId(anyString())).thenReturn(details);
		when(employeeDeayilRepo.findByReportingEmailId(anyString())).thenReturn(listDetail);
		assertEquals(detailResponse.getEmailId(),
				employeedeatilService.getEmployeeProfile("meghana@gmail.com").getEmailId());

	}

}
