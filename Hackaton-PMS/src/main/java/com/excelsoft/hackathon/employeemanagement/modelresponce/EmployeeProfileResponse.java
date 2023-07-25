package com.excelsoft.hackathon.employeemanagement.modelresponce;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.excelsoft.hackathon.employeemanagement.entity.EmployeeDetails;
import com.excelsoft.hackathon.employeemanagement.entity.FileData;
import com.excelsoft.hackathon.employeemanagement.entity.ProjectDetailes;
import com.excelsoft.hackathon.employeemanagement.entity.Skills;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EmployeeProfileResponse {

	private UUID employeeId;
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
	private List<ProjectDetailes> projectDetailes;
	private List<EmployeeDetails> details;
	private List<Skills> skills;
	private List<FileData> certification;

}
