package com.excelsoft.hackathon.employeemanagement.modelrequest;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeBulkRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<EmployeeDetailesRequest> employeeDetails;
	

}
