package com.excelsoft.hackathon.employeemanagement.modelrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectBulkInsert {

	private String projectCode;
	private String projectTitle;
    private String employeeCode;
}
