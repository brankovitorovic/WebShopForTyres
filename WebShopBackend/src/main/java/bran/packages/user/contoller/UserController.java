package bran.packages.user.contoller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bran.packages.user.dto.UserDTO;
import bran.packages.user.service.UserService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@GetMapping("/profil")
	public ResponseEntity<UserDTO> findUser() {
		return new ResponseEntity<>(userService.findUser(), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@Valid @RequestBody UserDTO request) {
		return new ResponseEntity<>(userService.save(request), HttpStatus.OK);
	}

	@PostMapping("/update") // it must be post for develop because of google chrome
	public ResponseEntity<String> update(@Valid @RequestBody UserDTO request) {
		return new ResponseEntity<>(userService.update(request), HttpStatus.OK);
	}

	@GetMapping("/delete") // it must be get instead of delete because of google chrome
	public ResponseEntity<?> delete() {
		userService.delete();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'PROVIDER')")
	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> findAll() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}
	
}
