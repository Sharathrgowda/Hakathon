package com.excelsoft.hackathon.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excelsoft.hackathon.employeemanagement.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData,String> {
   FileData findByName(String fileName);
	 public FileData findByEmployeeDetailsEmployeeCodeAndCertificatName(String employeeCode, String certificatName);
	 FileData findByCertificatName(String certificateName);
}
