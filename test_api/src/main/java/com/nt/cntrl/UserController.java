package com.nt.cntrl;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.yaml.snakeyaml.emitter.Emitable;

import com.nt.dto.APIResponse;
import com.nt.dto.UserRequestDTO;
import com.nt.dto.UserResponseDTO;
import com.nt.entity.EmailVerificationToken;
import com.nt.entity.User;
import com.nt.event.UserRegistrationCompleteEvent;
import com.nt.mapper.UserMapper;
import com.nt.service.EmailVerificationTokenService;
import com.nt.service.UserService;

@CrossOrigin("*")
@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Value("${frontend.url}")
	private String frontendURL;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private UserMapper mapper;

	@Autowired
	private EmailVerificationTokenService emailVerificationTokenService;

	@PostMapping
	public ResponseEntity<APIResponse> addUser(@RequestBody UserRequestDTO reqDto, final HttpServletRequest request) {

		try {

			boolean isUserAlreadyPresent = userService.checkIfAlreadyExists(reqDto);

			if (!isUserAlreadyPresent) {

				// Call service to add user
				User user = userService.addNewUser(reqDto);

				// If user creation is successful
				if (user != null) {
					APIResponse apiResponse = new APIResponse(mapper.toResponseDTO(user),
							"Success, Chcekc your maiand verify");
					publisher.publishEvent(new UserRegistrationCompleteEvent(user, applicationURL(request)));

					return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
				} else {
					// If user creation failed
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse(null, "Failed"));
				}
			} else {
				return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new APIResponse(null, "Already Exists"));
			}

		} catch (Exception e) {
			// Handle unexpected exceptions gracefully
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new APIResponse(null, "Internal Server Error"));
		}
	}

	private String applicationURL(HttpServletRequest request) {

		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	@GetMapping("/verifyRegistration")
	public Object verifyRegistration(@RequestParam("token") String token) {

		String result = emailVerificationTokenService.validateToken(token);
		if (result.equalsIgnoreCase("valid")) {
			
			return new RedirectView(frontendURL);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new APIResponse(null, "Invalid or Expired Token"));
	}

	@PostMapping("/resendVerificationlink")
	public ResponseEntity<APIResponse> resendEmailVerificationLink(@RequestBody UserRequestDTO dto, HttpServletRequest request) {

		User user = userService.checkIfExistsByEmail(dto.getEmail());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new APIResponse(null, "Invalid or Expired Token"));

		}

		EmailVerificationToken token = emailVerificationTokenService.generateNewVerificationToken(user, applicationURL(request));
		
		if (token != null) {

			String emailString = token.getUser().getEmail();
			String URL = applicationURL(request) + "/user/verifyRegistration?token="
					+ token.getToken();
			
			try {
				
				emailVerificationTokenService.sendVerificationMail(emailString, URL);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(new APIResponse(null, "Verification link is sent to your mail..."));

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse(null, "Internal Server Error"));

	}
	
	@PostMapping("/login")
	public ResponseEntity<APIResponse> loginValidateUser(@RequestBody UserRequestDTO dto) {
			
		String result = userService.validateLoginUser(dto.getUsername(), dto.getPassword());
		if(!result.equalsIgnoreCase("Failed")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new APIResponse(null, result));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new APIResponse(null, result));
		}
		
	}

}
