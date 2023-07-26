package com.excelsoft.hackathon.employeemanagement.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelsoft.hackathon.employeemanagement.entity.EmployeeDetails;
import com.excelsoft.hackathon.employeemanagement.entity.ProjectDetailes;
import com.excelsoft.hackathon.employeemanagement.exception.UserNotFoundException;
import com.excelsoft.hackathon.employeemanagement.modelrequest.ProjectBulkInsert;
import com.excelsoft.hackathon.employeemanagement.modelrequest.ProjectDetailRequest;
import com.excelsoft.hackathon.employeemanagement.repository.EmployeeDetailsRepository;
import com.excelsoft.hackathon.employeemanagement.repository.ProjectDetailesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProjectDetailesService {

	@Autowired
	private ProjectDetailesRepository projectRepository;
	@Autowired
	private EmployeeDetailsRepository employeeRepository;

	public ProjectDetailes projectResister(ProjectDetailRequest detailRequest) throws UserNotFoundException {
		try {
			var projectDetail = ProjectDetailes.builder()
					.employeeCode(Arrays.asList(employeeRepository.findByEmployeeCode(detailRequest.getEmployeeCode())))
					.projectCode(detailRequest.getProjectCode()).projectTitle(detailRequest.getProjectTitle()).build();
			return projectRepository.save(projectDetail);
		} catch (Exception e) {
			throw new UserNotFoundException("Unable to Save the Project", e);
		}

	}

	@Transactional
	public void saveProjectsInBulk(List<ProjectBulkInsert> projectBulkRequest) throws Exception {

		for (ProjectBulkInsert project : projectBulkRequest) {
			ProjectDetailes findByprojectTitle = projectRepository.findByprojectTitle(project.getProjectTitle());
			if (findByprojectTitle == null) {
				name(project);
			}
			ProjectDetailes findByprojectTitle2 = projectRepository.findByprojectTitle(project.getProjectTitle());
			EmployeeDetails findByEmployeeCode = employeeRepository.findByEmployeeCode(project.getEmployeeCode());
			findByEmployeeCode.getProjectId().add(findByprojectTitle2);
		}

	}

	@org.springframework.transaction.annotation.Transactional
	public void name(ProjectBulkInsert project) {
		projectRepository.save(ProjectDetailes.builder().projectCode(project.getProjectCode())
				.projectTitle(project.getProjectTitle()).build());
	}

	public void SaveExternalData(List<ProjectDetailRequest> detailRequests) {
		for (ProjectDetailRequest project : detailRequests) {
			ProjectDetailes findByprojectTitle = projectRepository.findByprojectTitle(project.getProjectTitle());
			if (findByprojectTitle == null) {
				projectRepository.save(ProjectDetailes.builder().projectCode(project.getProjectCode())
						.projectTitle(project.getProjectTitle()).build());
			}
			ProjectDetailes findByprojectTitle2 = projectRepository.findByprojectTitle(project.getProjectTitle());
			EmployeeDetails findByEmployeeCode = employeeRepository.findByEmployeeCode(project.getEmployeeCode());
			/*
			 * if (!skills.getEmployeeDetails().contains(details)) {
			 * details.getSkillId().add(skills);
			 */
			if(!findByEmployeeCode.getProjectId().contains(findByprojectTitle2)) {
			findByEmployeeCode.getProjectId().add(findByprojectTitle2);
			}
		}
	}
}
