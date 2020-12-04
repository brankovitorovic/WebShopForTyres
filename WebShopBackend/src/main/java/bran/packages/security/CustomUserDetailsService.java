package bran.packages.security;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bran.packages.user.entity.User;
import bran.packages.user.exception.UserNotFoundException;
import bran.packages.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User foundUser = repository.findByUsername(username).orElseThrow(
				() -> new UserNotFoundException(String.format("User with username: %s wasn't found!", username)));

		return new org.springframework.security.core.userdetails.User(foundUser.getUsername(), foundUser.getPassword(),
				getAuthorities(foundUser));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
				.collect(toList());
	}

}
