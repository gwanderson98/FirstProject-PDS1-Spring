package com.firstproject.FirstProjectPDS1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.firstproject.FirstProjectPDS1.dto.CredentialsDTO;
import com.firstproject.FirstProjectPDS1.dto.TokenDTO;
import com.firstproject.FirstProjectPDS1.entities.User;
import com.firstproject.FirstProjectPDS1.repositories.UserRepository;
import com.firstproject.FirstProjectPDS1.security.JWTUtil;
import com.firstproject.FirstProjectPDS1.services.exceptions.JWTAuthenticationException;
import com.firstproject.FirstProjectPDS1.services.exceptions.JWTAuthorizationException;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public TokenDTO authenticate(CredentialsDTO dto) {
		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getEmail(),
					dto.getPassword());
			authenticationManager.authenticate(authToken);
			String token = jwtUtil.generateToken(dto.getEmail());
			return new TokenDTO(dto.getEmail(), token);
		} catch (AuthenticationException e) {
			throw new JWTAuthenticationException("Bad credentials");
		}
	}
	
	public User authenticated() {
		try {

			UserDetails userdetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return userRepository.findByEmail(userdetails.getUsername());
		} catch (Exception e) {
			throw new JWTAuthorizationException("Acess denied!");
		}
	}

	public void validateSelfOrAdmin (Long userId) {
		User user = authenticated();
		if(user == null  || (!user.getId().equals(userId)) && !user.hasRole("ROLE_ADMIN")) {
			throw new JWTAuthorizationException("Acess denied!");
		}
	}
}