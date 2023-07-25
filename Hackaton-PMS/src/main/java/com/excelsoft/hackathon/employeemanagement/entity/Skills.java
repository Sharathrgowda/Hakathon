package com.excelsoft.hackathon.employeemanagement.entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "skills")
public class Skills {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID skillId;
	private String skillName;

	@JsonBackReference
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "skillId")
	private List<EmployeeDetails> employeeDetails;

}
