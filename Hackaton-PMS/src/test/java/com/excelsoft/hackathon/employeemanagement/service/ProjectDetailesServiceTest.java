package com.excelsoft.hackathon.employeemanagement.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.excelsoft.hackathon.employeemanagement.entity.EmployeeDetails;
import com.excelsoft.hackathon.employeemanagement.entity.ProjectDetailes;
import com.excelsoft.hackathon.employeemanagement.exception.UserNotFoundException;
import com.excelsoft.hackathon.employeemanagement.modelrequest.ProjectDetailRequest;
import com.excelsoft.hackathon.employeemanagement.repository.EmployeeDetailsRepository;
import com.excelsoft.hackathon.employeemanagement.repository.ProjectDetailesRepository;

@ExtendWith(MockitoExtension.class)
class ProjectDetailesServiceTest {

	@Mock
    private ProjectDetailesRepository projectRepo;
    @Mock
    private EmployeeDetailsRepository employeeDetailRepo;
    @InjectMocks
    private ProjectDetailesService projectService;
    
    @Test
    public void projectResister() throws UserNotFoundException {
        ProjectDetailes projectDetail=new ProjectDetailes();
        projectDetail.setProjectTitle("SMS A2C");
        ProjectDetailRequest projectDeatilReq=new ProjectDetailRequest();
        projectDeatilReq.setProjectTitle("SMS A2C");
        projectDeatilReq.setEmployeeCode("0000101");
        EmployeeDetails details =new EmployeeDetails();
        details.setEmailId("meghana@gmail.com");
        details.setEmployeeCode("0000101");
        when(employeeDetailRepo.findByEmployeeCode(any())).thenReturn(details);
        when(projectRepo.save(any())).thenReturn(projectDetail);
        assertEquals(projectDetail.getProjectTitle(), projectService.projectResister(projectDeatilReq).getProjectTitle());
//        assertEquals(details.getEmployeeCode(),  projectService.projectResister(projectDeatilReq).getEmployeeCode());
    }
     
    @Test
    public void projectResister1() throws UserNotFoundException {
        ProjectDetailes projectDetail=new ProjectDetailes();
        projectDetail.setProjectTitle("SMS A2C");
        ProjectDetailRequest projectDeatilReq=new ProjectDetailRequest();
        projectDeatilReq.setProjectTitle(" ");
        EmployeeDetails details =new EmployeeDetails();
        details.setEmailId("meghana@gmail.com");
        when(employeeDetailRepo.findByEmployeeCode(any())).thenReturn(details);
        when(projectRepo.save(any())).thenReturn(projectDetail);
//        assertEquals(projectDetail.getProjectTitle(), projectService.projectResister(projectDeatilReq).getProjectTitle());
  assertThrows(UserNotFoundException.class,  () -> projectService.projectResister(projectDeatilReq));
     
    }
}
