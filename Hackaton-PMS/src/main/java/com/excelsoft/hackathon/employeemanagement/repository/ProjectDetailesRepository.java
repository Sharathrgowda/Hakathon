package com.excelsoft.hackathon.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelsoft.hackathon.employeemanagement.entity.ProjectDetailes;
@Repository
public interface ProjectDetailesRepository extends JpaRepository<ProjectDetailes, String>{
	
 ProjectDetailes findByprojectTitle(String projectName);
}
