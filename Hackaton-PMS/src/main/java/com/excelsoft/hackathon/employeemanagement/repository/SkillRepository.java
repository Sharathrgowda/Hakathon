package com.excelsoft.hackathon.employeemanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excelsoft.hackathon.employeemanagement.entity.Skills;

public interface SkillRepository extends JpaRepository<Skills, UUID> {
 	 Skills findByskillName(String skillName);
	 Skills findBySkillNameIgnoreCase(String skillName);
}
