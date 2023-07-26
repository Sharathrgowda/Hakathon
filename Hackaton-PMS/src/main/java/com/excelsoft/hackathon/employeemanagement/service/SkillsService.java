package com.excelsoft.hackathon.employeemanagement.service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excelsoft.hackathon.employeemanagement.entity.EmployeeDetails;
import com.excelsoft.hackathon.employeemanagement.entity.Skills;
import com.excelsoft.hackathon.employeemanagement.exception.DuplicateDataExcetion;
import com.excelsoft.hackathon.employeemanagement.exception.UserNotFoundException;
import com.excelsoft.hackathon.employeemanagement.modelrequest.SkillRequest;
import com.excelsoft.hackathon.employeemanagement.repository.EmployeeDetailsRepository;
import com.excelsoft.hackathon.employeemanagement.repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class SkillsService {

	private final EmployeeDetailsRepository employeeRepository;

	private final SkillRepository skillRepository;

	public Skills skillRegister(SkillRequest skillRequest) throws UserNotFoundException {
		try {
			var skillsDetailes = Skills.builder()
					.employeeDetails(
							Arrays.asList(employeeRepository.findByEmployeeCode(skillRequest.getEmployeeCode())))
					.skillName(skillRequest.getSkillName()).build();

			return skillRepository.save(skillsDetailes);
		} catch (Exception e) {

			throw new RuntimeException("Unable to Save Skill");
		}
	}

	public Set<String> getSkills() {
		return skillRepository.findAll().stream().map(x -> x.getSkillName()).collect(Collectors.toSet());
	}

	public void addSkills(SkillRequest skillRequest) throws UserNotFoundException, DuplicateDataExcetion {
		EmployeeDetails details = employeeRepository.findByEmployeeCode(skillRequest.getEmployeeCode());
		String skillNameLowerCase = skillRequest.getSkillName().toLowerCase(); // convert to lowercase
		Skills skills = skillRepository.findBySkillNameIgnoreCase(skillNameLowerCase); // find ignoring case

		if (null != details) {
			if (null == skills) {
				skills = skillRepository.save(
						Skills.builder().skillName(skillNameLowerCase).employeeDetails(Arrays.asList(details)).build());
			} else {
				boolean containsSkill = skills.getEmployeeDetails().stream()
						.anyMatch(employee -> employee.getEmployeeCode().equals(skillRequest.getEmployeeCode()));
				if (containsSkill) {
					throw new DuplicateDataExcetion("Skill already present for the employee data");
				} else {
					details.getSkillId().add(skills);
				}
			}
		} else {
			throw new UserNotFoundException("User name not found");
		}
	}
	
	public void deleteSkill(SkillRequest skillRequest) throws UserNotFoundException, DuplicateDataExcetion {
        EmployeeDetails details = employeeRepository.findByEmployeeCode(skillRequest.getEmployeeCode());
        String skillNameLowerCase = skillRequest.getSkillName().toLowerCase(); // convert to lowercase
        Skills skills = skillRepository.findBySkillNameIgnoreCase(skillNameLowerCase); // find ignoring case

        if (null != details) {
            if(details.getSkillId().contains(skills)) {
                 details.getSkillId().remove(skills);
            
        }  else {
                boolean containsSkill = skills.getEmployeeDetails().stream()
                        .anyMatch(employee -> employee.getEmployeeCode().equals(skillRequest.getEmployeeCode()));
                if (!containsSkill) {
                    throw new DuplicateDataExcetion("Skill not present for the employee");
                } 
            }
        } else {
            throw new UserNotFoundException("User name not found");
        }
    }

}
