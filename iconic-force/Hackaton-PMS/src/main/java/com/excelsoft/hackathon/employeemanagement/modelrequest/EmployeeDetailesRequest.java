package com.excelsoft.hackathon.employeemanagement.modelrequest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailesRequest {

	private String employeeCode;
	private String emailId;
	private String employeeName;
	private String mobileNumber;
	private String designation;
	private String department;
	private String employeeGrade;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date joinDate;
	private String reportingEmailId;
	private String gender;
	private String location;

}
