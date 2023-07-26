package com.excelsoft.hackathon.employeemanagement.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employeeDetails")
public class EmployeeDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID employeeId;
	private String employeeCode;
	@Column(unique = true)
	private String emailId;
	private String employeeName;
	private String mobileNumber;
	private String designation;
	private String department;
	private String employeeGrade;
	private Date joinDate;
	private String reportingEmailId;
	private String gender;
	private String location;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "employeeWiseProjectdetails", joinColumns = @JoinColumn(name = "employeeId"), inverseJoinColumns = @JoinColumn(name = "projectId"))
	private List<ProjectDetailes> projectId;


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "employeeSkills", joinColumns = @JoinColumn(name = "employeeCode"), inverseJoinColumns = @JoinColumn(name = "skillId"))
	private List<Skills> skillId;
	
	@ManyToMany(mappedBy = "employeeDetails")
	private List<FileData> certificate;
}
