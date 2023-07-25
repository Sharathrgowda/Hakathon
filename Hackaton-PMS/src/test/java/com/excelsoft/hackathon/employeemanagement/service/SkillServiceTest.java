package com.excelsoft.hackathon.employeemanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.excelsoft.hackathon.employeemanagement.entity.EmployeeDetails;
import com.excelsoft.hackathon.employeemanagement.entity.Skills;
import com.excelsoft.hackathon.employeemanagement.exception.DuplicateDataExcetion;
import com.excelsoft.hackathon.employeemanagement.exception.UserNotFoundException;
import com.excelsoft.hackathon.employeemanagement.modelrequest.SkillRequest;
import com.excelsoft.hackathon.employeemanagement.repository.EmployeeDetailsRepository;
import com.excelsoft.hackathon.employeemanagement.repository.SkillRepository;

@ExtendWith(MockitoExtension.class)
public class SkillServiceTest {
	@Mock
	private EmployeeDetailsRepository employeeDetailRepo;

	@Mock
	private SkillRepository skillRepo;

	@InjectMocks
	private SkillsService skillService;
	@Mock
	private SkillsService skillService1;

	@Test
	public void skillRegister() throws UserNotFoundException {
		Skills detail = new Skills(); // stabbing
		detail.setSkillName("java");

		SkillRequest request = new SkillRequest();
		request.setEmployeeCode("000000101");
		request.setSkillName("java");

		EmployeeDetails empDetail = new EmployeeDetails();
		empDetail.setEmployeeCode("000000101");

		when(employeeDetailRepo.findByEmployeeCode(any())).thenReturn(empDetail);
		when(skillRepo.save(any())).thenReturn(detail);
		assertEquals(detail.getSkillName(), skillService.skillRegister(request).getSkillName());
	}

	@Test
	public void addSkillsTest() throws UserNotFoundException, DuplicateDataExcetion {

		List<Skills> skill = new ArrayList<Skills>();

		EmployeeDetails empDetail = new EmployeeDetails();
		empDetail.setSkillId(skill);

		when(employeeDetailRepo.findByEmployeeCode(any())).thenReturn(empDetail);
		when(skillRepo.findBySkillNameIgnoreCase(any())).thenReturn(null);
		SkillRequest request = new SkillRequest();
		request.setSkillName("java");
		request.setEmployeeCode("000000101");
		assert (skillService.addSkills(request));

//		assertEquals(skill.getSkillName(),skillService.addSkills(request));

	}

	@Test
	public void addSkills2() throws UserNotFoundException, DuplicateDataExcetion {

		List<Skills> skill = new ArrayList<Skills>();
		List<EmployeeDetails> empDetail1 = new ArrayList<EmployeeDetails>();

		EmployeeDetails empDetail = new EmployeeDetails();
		empDetail.setSkillId(skill);
		empDetail.setDepartment("pls");
		empDetail.setDesignation("testing");
		empDetail.setEmailId("megha@gmail.com");
		empDetail.setEmployeeCode("000000101");
		empDetail.setEmployeeGrade("A");
		empDetail.setEmployeeId(UUID.randomUUID());
		empDetail.setEmployeeName("meghana");
		empDetail.setGender("f");
		empDetail1.add(empDetail);

		Skills skills = new Skills();
		skills.setSkillId(UUID.randomUUID());
		skills.setSkillName("mava");
		skills.setEmployeeDetails(empDetail1);
		when(employeeDetailRepo.findByEmployeeCode(any())).thenReturn(empDetail);
		when(skillRepo.findBySkillNameIgnoreCase(any())).thenReturn(skills);
		SkillRequest request = new SkillRequest();
		request.setSkillName("mava");
		request.setEmployeeCode("00001");
		assert (skillService.addSkills(request));
//		assertEquals(skill.getSkillName(),skillService.addSkills(request));

	}

	@Test
	public void addSkills() throws UserNotFoundException, DuplicateDataExcetion {

		List<Skills> skill = new ArrayList<Skills>();

		EmployeeDetails empDetail = new EmployeeDetails();
		empDetail.setSkillId(skill);

		when(employeeDetailRepo.findByEmployeeCode(any())).thenReturn(empDetail);
		when(skillRepo.findBySkillNameIgnoreCase(any())).thenReturn(null);
		SkillRequest request = new SkillRequest();
		request.setSkillName("java");
		request.setEmployeeCode("000000101");
		assert (skillService.addSkills(request));

//		assertEquals(skill.getSkillName(),skillService.addSkills(request));

	}

	@Test
	public void addSkills2hg() throws UserNotFoundException, DuplicateDataExcetion {

		List<Skills> skill = new ArrayList<Skills>();
		List<EmployeeDetails> empDetail1 = new ArrayList<EmployeeDetails>();

		EmployeeDetails empDetail = new EmployeeDetails();
		empDetail.setSkillId(skill);
		empDetail.setDepartment("pls");
		empDetail.setDesignation("testing");
		empDetail.setEmailId("megha@gmail.com");
		empDetail.setEmployeeCode("000000101");
		empDetail.setEmployeeGrade("A");
		empDetail.setEmployeeId(UUID.randomUUID());
		empDetail.setEmployeeName("meghana");
		empDetail.setGender("f");
		empDetail1.add(empDetail);

		Skills skills = new Skills();
		skills.setSkillId(UUID.randomUUID());
		skills.setSkillName("mava");
		skills.setEmployeeDetails(empDetail1);
		when(employeeDetailRepo.findByEmployeeCode(any())).thenReturn(empDetail);
		when(skillRepo.findBySkillNameIgnoreCase(any())).thenReturn(skills);
		SkillRequest request = new SkillRequest();
		request.setSkillName("mava");
		request.setEmployeeCode("000000101");
//		assert(skillService.addSkills(request));
//		assertEquals(skill.getSkillName(),skillService.addSkills(request));
		assertThrows(DuplicateDataExcetion.class, () -> skillService.addSkills(request));
	}


	@Test
	public void deleteSkill() throws UserNotFoundException, DuplicateDataExcetion {
		
		List<EmployeeDetails> empDetail=new ArrayList<EmployeeDetails>();
	List<Skills> skillList=new ArrayList<Skills>();
		
		EmployeeDetails detail=new EmployeeDetails();
		detail.setEmployeeCode("000000101");
		
		detail.setDepartment("pls");
		detail.setDesignation("testing");
		detail.setEmailId("megha@gmail.com");
		detail.setEmployeeCode("000000101");
		detail.setEmployeeGrade("A");
		detail.setEmployeeId(UUID.randomUUID());
		detail.setEmployeeName("meghana");
		detail.setGender("f");
		empDetail.add(detail);
		
		Skills skill=new Skills();
		skill.setEmployeeDetails(empDetail);
		skill.setSkillId(UUID.randomUUID());
		skill.setSkillName("java");
		skillList.add(skill);
		
		detail.setSkillId(skillList);

		
		SkillRequest skillRequest=new SkillRequest();
		skillRequest.setEmployeeCode("000000101");
		skillRequest.setSkillName("java");
		when(employeeDetailRepo.findByEmployeeCode(any())).thenReturn(detail);
	    when(skillRepo.findBySkillNameIgnoreCase(any())).thenReturn(skill);
	    assertTrue(skillService.deleteSkill(skillRequest));
	}

	
	@Test
	public void deleteSkill1() throws UserNotFoundException, DuplicateDataExcetion {
		
		List<EmployeeDetails> empDetail=new ArrayList<EmployeeDetails>();
	List<Skills> skillList=new ArrayList<Skills>();
		
		EmployeeDetails detail=new EmployeeDetails();
		detail.setEmployeeCode("000000101");
		
		detail.setDepartment("pls");
		detail.setDesignation("testing");
		detail.setEmailId("megha@gmail.com");
		detail.setEmployeeCode("000000101");
		detail.setEmployeeGrade("A");
		detail.setEmployeeId(UUID.randomUUID());
		detail.setEmployeeName("meghana");
		detail.setGender("f");
		empDetail.add(detail);
		
		Skills skill=new Skills();
		skill.setEmployeeDetails(empDetail);
		skill.setSkillId(UUID.randomUUID());
		skill.setSkillName("jabnm,");
		
		
		Skills skill1=new Skills();
		skill.setEmployeeDetails(empDetail);
		skill.setSkillId(UUID.randomUUID());
		skill.setSkillName("xfgdsg,");
		skillList.add(skill1);
		
		detail.setSkillId(skillList);

		
		SkillRequest skillRequest=new SkillRequest();
		skillRequest.setEmployeeCode("00000011");
		skillRequest.setSkillName("java");
		when(employeeDetailRepo.findByEmployeeCode(any())).thenReturn(detail);
	    when(skillRepo.findBySkillNameIgnoreCase(any())).thenReturn(skill);
	assertThrows(DuplicateDataExcetion.class, ()-> skillService.deleteSkill(skillRequest));
	}
}
