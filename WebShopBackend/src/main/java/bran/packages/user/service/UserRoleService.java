package bran.packages.user.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import bran.packages.user.dto.UserRoleDTO;
import bran.packages.user.entity.UserRole;
import bran.packages.user.exception.InvalidUserRoleInfoException;
import bran.packages.user.exception.UserRoleNotFoundException;
import bran.packages.user.mapper.UserRoleMapper;
import bran.packages.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserRoleService {

	private final UserRoleRepository repository;

	private final UserRoleMapper mapper;

	@Transactional
	public void deleteById(Long existingId) {
		if (roleNotFound(existingId))
			throw new UserRoleNotFoundException(String.format("User role with id %d wasn't found!", existingId));

		repository.deleteById(existingId);
	}

	@Transactional
	public UserRoleDTO save(UserRoleDTO request) {
		if (nameExists(request.getName()))
			throw new InvalidUserRoleInfoException(
					String.format("User role with name: %s already exists!", request.getName()));

		UserRole userRole = repository.save(mapper.fromDTO(request));

		return mapper.toDTO(userRole);
	}

	@Transactional
	public UserRoleDTO update(Long existingId, UserRoleDTO request) {

		if (nameExists(request.getName()))
			throw new InvalidUserRoleInfoException(
					String.format("User role with name: %s already exists!", request.getName()));

		request.setId(existingId);
		UserRole role = repository.save(mapper.fromDTO(request));

		return mapper.toDTO(role);
	}

	public UserRoleDTO findById(Long id) {
		return repository.findById(id).map(mapper::toDTO).orElseThrow(
				() -> new UserRoleNotFoundException(String.format("User role with id %d wasn't found!", id)));
	}

	public List<UserRoleDTO> findAll() {
		return mapper.toDTOList(repository.findAll());
	}

	private boolean roleNotFound(Long existingId) {
		return repository.findById(existingId).isEmpty();
	}

	private boolean nameExists(String name) {
		return repository.findByName(name).isPresent();
	}
	
}
