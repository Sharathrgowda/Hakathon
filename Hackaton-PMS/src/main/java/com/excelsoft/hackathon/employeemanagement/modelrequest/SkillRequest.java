package com.excelsoft.hackathon.employeemanagement.modelrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillRequest {
	
	private String employeeCode;
	private String skillName;

}
