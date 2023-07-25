package com.excelsoft.hackathon.employeemanagement.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.excelsoft.hackathon.employeemanagement.config.JwtService;
import com.excelsoft.hackathon.employeemanagement.entity.User;
import com.excelsoft.hackathon.employeemanagement.exception.UserNotFoundException;
import com.excelsoft.hackathon.employeemanagement.modelrequest.AuthenticationRequest;
import com.excelsoft.hackathon.employeemanagement.modelrequest.RegisterRequest;
import com.excelsoft.hackathon.employeemanagement.modelresponce.AuthenticationResponse;
import com.excelsoft.hackathon.employeemanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {
		var user = User.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
				// .role() role is disabled
				.build();
		repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) throws UserNotFoundException {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			var user = repository.findByEmail(request.getEmail()).orElseThrow();
			var jwtToken = jwtService.generateToken(user);
			if (user != null) {
				return AuthenticationResponse.builder().token(jwtToken).build();
			} else {
				throw new UserNotFoundException("User Not found");
			}
		} catch (Exception exception) {
			throw new UserNotFoundException("User Not found", exception);
		}
	}

}
