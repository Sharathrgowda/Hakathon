package com.excelsoft.hackathon.employeemanagement.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excelsoft.hackathon.employeemanagement.entity.EmployeeDetails;
import com.excelsoft.hackathon.employeemanagement.entity.FileData;
import com.excelsoft.hackathon.employeemanagement.repository.EmployeeDetailsRepository;
import com.excelsoft.hackathon.employeemanagement.repository.FileDataRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StorageService {

	private final EmployeeDetailsRepository employeeRepository;

	private final FileDataRepository fileDataRepository;

	public String uploadImageToFileSystem(MultipartFile file, String employeeCode, String certificatName)
			throws IllegalStateException, IOException {
		String FOLDER_PATH = "D:/Images/";

		File folder = new File(FOLDER_PATH);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		System.out.println(FOLDER_PATH);
		String filePath = FOLDER_PATH + employeeCode + file.getOriginalFilename();

		EmployeeDetails details = employeeRepository.findByEmployeeCode(employeeCode);
		if (details != null) {
			var fileData = FileData.builder()
					.employeeDetails(Arrays.asList(employeeRepository.findByEmployeeCode(employeeCode)))
					.name(file.getOriginalFilename()).type(file.getContentType()).filePath(filePath)
					.certificatName(certificatName).build();
			fileDataRepository.save(fileData);
			FileOutputStream fos = new FileOutputStream(filePath);
			fos.write(file.getBytes());
			fos.close();
			if (fileData != null) {
				return "file uploaded successfully";
			}
		}
		return null;
	}

	public String downloadImage(String employeeCode, String certificatName) throws IOException {
		FileData filedata = fileDataRepository.findByEmployeeDetailsEmployeeCodeAndCertificatName(employeeCode,
				certificatName);

		if (filedata != null) {
			return filedata.getFilePath().substring(filedata.getFilePath().lastIndexOf('/') + 1);

		}

		return null;

	}

	public void deleteCertificates(String employeeCode, String certificateName) throws FileNotFoundException {
		EmployeeDetails details = employeeRepository.findByEmployeeCode(employeeCode);
		FileData data = fileDataRepository.findByCertificatName(certificateName);
		if (details != null) {
			String filePath = data.getFilePath();
			File file = new File(filePath);
			file.delete();
			fileDataRepository.delete(data);
		}else {
			throw new FileNotFoundException();
		}
	}

}
