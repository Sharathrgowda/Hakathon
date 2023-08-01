package com.excelsoft.hackathon.employeemanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excelsoft.hackathon.employeemanagement.entity.EmployeeDetails;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, UUID> {

	EmployeeDetails findByEmailId(String emailId);

	EmployeeDetails findByEmployeeCode(String employeeCode);

	List<EmployeeDetails> findByReportingEmailId(String reportingEmailId);
}
