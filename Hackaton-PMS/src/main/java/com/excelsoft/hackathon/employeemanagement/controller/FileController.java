package com.excelsoft.hackathon.employeemanagement.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.excelsoft.hackathon.employeemanagement.modelresponce.Response;
import com.excelsoft.hackathon.employeemanagement.service.StorageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/file-controller")
@RequiredArgsConstructor
@CrossOrigin
public class FileController {

	private final StorageService service;

	@PostMapping()
	public Response<?> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam String employeeCode,
			@RequestParam String certificatName) throws IllegalStateException, IOException {
		if (service.uploadImageToFileSystem(file, employeeCode, certificatName) != null) {
			return Response.builder().statusCode("200").discription("Certificate added successfully").build();
		} else {
			return Response.builder().statusCode("400").discription("Certificate already present").build();
		}
	}

	@GetMapping("/path/{employeeCode}/{certificatName}")
	public ResponseEntity<?> downloadImage(@PathVariable String employeeCode, @PathVariable String certificatName)
			throws IOException {
		String downloadImage = service.downloadImage(employeeCode, certificatName);
		return ResponseEntity.status(HttpStatus.OK).body(downloadImage);

	}

	@DeleteMapping("/path/{employeeCode}/{certificatName}")
	public Response<?> deleteImage(@PathVariable String employeeCode, @PathVariable String certificatName) {
		try {
			service.deleteCertificates(employeeCode, certificatName);
			return Response.builder().statusCode("200").discription("certificates deleted successfully").build();
		} catch (Exception e) {
			return Response.builder().statusCode("400").discription("certificates not deleted ").build();
		}
	}

}
