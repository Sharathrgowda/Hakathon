package com.excelsoft.hackathon.employeemanagement.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.excelsoft.hackathon.employeemanagement.entity.EmployeeDetails;
import com.excelsoft.hackathon.employeemanagement.entity.User;
import com.excelsoft.hackathon.employeemanagement.exception.UserNotFoundException;
import com.excelsoft.hackathon.employeemanagement.modelrequest.EmployeeBulkRequest;
import com.excelsoft.hackathon.employeemanagement.modelrequest.EmployeeDetailesRequest;
import com.excelsoft.hackathon.employeemanagement.modelrequest.UserRequest;
import com.excelsoft.hackathon.employeemanagement.modelresponce.EmployeeProfileResponse;
import com.excelsoft.hackathon.employeemanagement.repository.EmployeeDetailsRepository;
import com.excelsoft.hackathon.employeemanagement.repository.UserRepository;

@Service
//@RequiredArgsConstructor
public class EmployeeDetailesService {

	@Autowired
	private EmployeeDetailsRepository empDetailRepo;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/* This method is used for the registering one employee at a time */
	public EmployeeDetails register(EmployeeDetailesRequest empDetaileRequest) {
		var empDetail = EmployeeDetails.builder().employeeCode(empDetaileRequest.getEmployeeCode())
				.emailId(empDetaileRequest.getEmailId()).employeeName(empDetaileRequest.getEmployeeName())
				.mobileNumber(empDetaileRequest.getMobileNumber()).designation(empDetaileRequest.getDesignation())
				.department(empDetaileRequest.getDepartment()).employeeGrade(empDetaileRequest.getEmployeeGrade())
				.joinDate(empDetaileRequest.getJoinDate()).reportingEmailId(empDetaileRequest.getReportingEmailId())
				.gender(empDetaileRequest.getGender()).location(empDetaileRequest.getLocation()).build();
		return empDetailRepo.save(empDetail);
	}

	/* This method is used for registering the multiple employee at a time */
	public void saveEmployeesInBulk(EmployeeBulkRequest employeeBulkRequest) {
		List<EmployeeDetails> employeeDetails = new ArrayList<EmployeeDetails>();

		employeeBulkRequest.getEmployeeDetails().stream().forEach(employee -> {

			EmployeeDetails employeeDetail = EmployeeDetails.builder().emailId(employee.getEmailId())
					.employeeCode(employee.getEmployeeCode()).reportingEmailId(employee.getReportingEmailId())
					.mobileNumber(employee.getMobileNumber()).department(employee.getDepartment())
					.designation(employee.getDesignation()).employeeGrade(employee.getEmployeeGrade())
					.joinDate(employee.getJoinDate()).location(employee.getLocation())
					.employeeName(employee.getEmployeeName()).gender(employee.getGender()).build();

			employeeDetails.add(employeeDetail);

		});

		empDetailRepo.saveAll(employeeDetails);
	}

	/*
	 * This method is used for fetching the head and the employee working under them
	 */
	public EmployeeProfileResponse getEmployeeProfile(String emailID) throws UserNotFoundException {

		EmployeeDetails employeeDetailes = empDetailRepo.findByEmailId(emailID);
		List<EmployeeDetails> findByReportingEmailId = empDetailRepo.findByReportingEmailId(emailID);
		if (null != employeeDetailes) {

			return (null != findByReportingEmailId) ? EmployeeProfileResponse.builder()
					.employeeName(employeeDetailes.getEmployeeName()).emailId(employeeDetailes.getEmailId())
					.employeeCode(employeeDetailes.getEmployeeCode()).employeeGrade(employeeDetailes.getEmployeeGrade())
					.mobileNumber(employeeDetailes.getMobileNumber()).designation(employeeDetailes.getDesignation())
					.department(employeeDetailes.getDepartment()).joinDate(employeeDetailes.getJoinDate())
					.gender(employeeDetailes.getGender()).location(employeeDetailes.getLocation()).certification(employeeDetailes.getCertificate())
					.projectDetailes(employeeDetailes.getProjectId()).skills(employeeDetailes.getSkillId())
					.employeeId(employeeDetailes.getEmployeeId())
					.details(findByReportingEmailId).reportingEmailId(employeeDetailes.getReportingEmailId()).build()
					: EmployeeProfileResponse.builder().employeeName(employeeDetailes.getEmployeeName())
							.emailId(employeeDetailes.getEmailId()).employeeCode(employeeDetailes.getEmployeeCode())
							.employeeGrade(employeeDetailes.getEmployeeGrade())
							.mobileNumber(employeeDetailes.getMobileNumber())
							.designation(employeeDetailes.getDesignation()).department(employeeDetailes.getDepartment())
							.joinDate(employeeDetailes.getJoinDate()).gender(employeeDetailes.getGender())
							.location(employeeDetailes.getLocation()).skills(employeeDetailes.getSkillId())
							.projectDetailes(employeeDetailes.getProjectId())
							.reportingEmailId(employeeDetailes.getReportingEmailId())
							.employeeId(employeeDetailes.getEmployeeId())
							.projectDetailes(employeeDetailes.getProjectId()).certification(employeeDetailes.getCertificate()).build();

		} else {
			throw new UserNotFoundException("User not found for email " + emailID);
		}

	}

	/*
	 * This method is used to save the employee details that is coming from the
	 * external API
	 */
	@Transactional(rollbackFor = SQLException.class)
	public void saveEmp(List<EmployeeDetailesRequest> employeeDetails) {
		List<EmployeeDetails> empDetails = new ArrayList<EmployeeDetails>();
		for (EmployeeDetailesRequest employee : employeeDetails) {

			EmployeeDetails employeeDetail = EmployeeDetails.builder().emailId(employee.getEmailId())
					.employeeCode(employee.getEmployeeCode()).department(employee.getDepartment())
					.reportingEmailId(employee.getReportingEmailId()).mobileNumber(employee.getMobileNumber())
					.designation(employee.getDesignation()).employeeGrade(employee.getEmployeeGrade())
					.joinDate(employee.getJoinDate()).location(employee.getLocation())
					.employeeName(employee.getEmployeeName()).gender(employee.getGender()).build();
			empDetails.add(employeeDetail);
		}
		empDetailRepo.saveAll(empDetails);
	}

	public void savesuer(List<UserRequest> userRequests) {
		List<User> user1 = new ArrayList<User>();
		for (UserRequest x : userRequests) {

			String encode = passwordEncoder.encode("qwerty");
			User user = User.builder().email(x.getEmailId()).password(encode).build();
			user1.add(user);
		}
		userRepository.saveAll(user1);
	}

}
