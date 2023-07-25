package com.excelsoft.hackathon.employeemanagement.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
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

@Entity
@Table(name = "FILE_DATA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String certificatId;

	private String certificatName;

	private String name;
	
	private String type;
	
	private String filePath;
	
	 @JsonBackReference
		@ManyToMany(cascade = CascadeType.DETACH.MERGE.PERSIST.REFRESH)
		@JoinTable(name = "employeeFile", joinColumns = @JoinColumn(name = "certificatId"), inverseJoinColumns = @JoinColumn(name = "employeeCode"))
		private List<EmployeeDetails> employeeDetails;

}
