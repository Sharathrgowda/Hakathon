package com.excelsoft.hackathon.employeemanagement.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "projectDetailes")
public class ProjectDetailes {
	
	@Id
	private String projectCode;
	private String projectTitle;
	
	@JsonBackReference
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "projectId")
//	@JoinTable(name = "employeeWiseProjectdetails", joinColumns = @JoinColumn(name = "project"), inverseJoinColumns = @JoinColumn(name = "employeeCode"))
	private List<EmployeeDetails> employeeCode;
	
	
	

	
}
