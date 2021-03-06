package fr.ippon.tatami.security;

import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fr.ippon.tatami.domain.User;
import fr.ippon.tatami.repository.UserRepository;

@Service
public class AuthenticationService {

	@Inject
	private UserRepository userRepository;

	public User getCurrentUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		org.springframework.security.core.userdetails.User springSecurityUser = (org.springframework.security.core.userdetails.User) securityContext
		                .getAuthentication().getPrincipal();

		return userRepository.findUserByLogin(springSecurityUser.getUsername());
	}
}