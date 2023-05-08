package com.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeControler {

	@PreAuthorize("hasRole('NORMAL')")
	@GetMapping("/normal")
	public ResponseEntity<String> normalUser() {
		return ResponseEntity.ok("yes, i am normal");
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public ResponseEntity<String> adminUser() {
		return ResponseEntity.ok("yes, i am admin");
	}
	
	
	@GetMapping("/public")
	public ResponseEntity<String> publicUser() {
		return ResponseEntity.ok("yes, i am public user");
	}

}
