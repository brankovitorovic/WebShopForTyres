package bran.packages.user.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import bran.packages.security.JWTConstants;
import bran.packages.user.dto.UserDTO;
import bran.packages.user.dto.UserRoleDTO;
import bran.packages.user.entity.User;
import bran.packages.user.exception.InvalidUserInfoException;
import bran.packages.user.exception.ProblemWithDatabase;
import bran.packages.user.exception.UserNotFoundException;
import bran.packages.user.mapper.UserMapper;
import bran.packages.user.repository.UserRepository;
import bran.packages.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final UserRoleRepository userRoleRepository;

	private final UserMapper mapper;

	@Transactional
	public String save(UserDTO request) {
		if (usernameExists(request.getUsername()))
			throw new InvalidUserInfoException(
					String.format("User with username: %s already exists!", request.getUsername()));

		if (invalidUserRoles(request))
			throw new InvalidUserInfoException("Invalid user roles!");

		request.setPassword(hashPassword(request.getPassword()));

		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>(); // we will set every new save to be user
		UserRoleDTO role = new UserRoleDTO();
		role.setId(Long.valueOf("2"));
		role.setName("USER");
		roles.add(role);
		request.setRoles(roles);

		User user = null;
		try {
			user = userRepository.save(mapper.fromDTO(request));
		} catch (Exception e) {
			throw new ProblemWithDatabase("Problem with saving user to database!");
		}
		return createToken(user);
	}

	@Transactional
	public String update(UserDTO request) {
		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		// if user want to change username, we need to check if new username already exist
		if ( ! request.getUsername().equals(username)) {
			if (usernameExists(request.getUsername())) {
				throw new InvalidUserInfoException(
						String.format("User with username: %s already exists!", request.getUsername()));
			}
		}
		if (invalidUserRoles(request))
			throw new InvalidUserInfoException("Invalid user roles!");

		if (request.isPasswordPlain())
			request.setPassword(hashPassword(request.getPassword()));

		User user = mapper.fromDTO(request);
		user.setId(userRepository.findId(username));
		try {
			user = userRepository.save(user);
		} catch (Exception e) {
			throw new ProblemWithDatabase("Problem with saving user to database!");
		}
		return createToken(user);
	}

	@Transactional
	public void delete() {
		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (!usernameExists(username)) {
			throw new InvalidUserInfoException(String.format("User with username: %s does not exists!", username));
		}
		try {
			userRepository.deleteByUsername(username);
		} catch (Exception e) {
			throw new ProblemWithDatabase("Problem with saving user to database!");
		}
	}

	public UserDTO findUser() {
		final String username = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByUsername(username).map(mapper::toDTO).orElseThrow(
				() -> new UserNotFoundException(String.format("User with username: %s wasn't found!", username)));
	}

	public List<UserDTO> findAll() {
		return mapper.toDTOList(userRepository.findAll());
	}

	public String updateToken(String username) { // when user change username or password and when token expire so it can be updated
		return createToken(userRepository.findByUsername(username).get());
	}

	private String createToken(User user) {

		String rolesToken = String.format("[ROLE_%s]", user.getRoles().stream().findFirst().get().getName());

		String token = JWT.create().withSubject(user.getUsername()).withClaim("Role", rolesToken)
				.withExpiresAt(new Date(System.currentTimeMillis() + JWTConstants.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(JWTConstants.SECRET_KEY));

		return JWTConstants.TOKEN_PREFIX + token;
	}

	private boolean usernameExists(String username) {
		return userRepository.findByUsername(username).isPresent();
	}

	private boolean invalidUserRoles(UserDTO request) {
		return request.getRoles().stream().anyMatch(this::roleNotFound);
	}

	private boolean roleNotFound(UserRoleDTO role) {
		return userRoleRepository.findById(role.getId()).isEmpty();
	}

	private String hashPassword(String plainText) {
		return BCrypt.hashpw(plainText, BCrypt.gensalt());
	}

}
