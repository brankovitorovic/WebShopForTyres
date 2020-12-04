package bran.packages.user.contoller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bran.packages.user.dto.UserRoleDTO;
import bran.packages.user.service.UserRoleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/role")
public class UserRoleController {

	private final UserRoleService userRoleService;
	
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/existingId={existingId}")
    public ResponseEntity<?> deleteById(@PathVariable Long existingId) {
        userRoleService.deleteById(existingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<UserRoleDTO> save(@Valid @RequestBody UserRoleDTO request) {
        return new ResponseEntity<>(userRoleService.save(request), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/existingId={existingId}")
    public ResponseEntity<UserRoleDTO> update(@PathVariable Long existingId, @Valid @RequestBody UserRoleDTO request) {
        return new ResponseEntity<>(userRoleService.update(existingId, request), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/id={id}")
    public ResponseEntity<UserRoleDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userRoleService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserRoleDTO>> findAll() {
        return new ResponseEntity<>(userRoleService.findAll(), HttpStatus.OK);
    }


}
