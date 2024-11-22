package com.nt.cntrl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.APIResponse;

@CrossOrigin("*")
@RestController
public class DashboardController {

	@GetMapping("/hello")
	public ResponseEntity<APIResponse> hello() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new APIResponse(null, "hello User Testing"));
	}
}
