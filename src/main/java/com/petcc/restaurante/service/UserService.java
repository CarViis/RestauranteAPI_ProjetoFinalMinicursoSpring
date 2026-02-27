package com.petcc.restaurante.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.petcc.restaurante.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.petcc.restaurante.model.User user = userRepository.findByLogin(username)
			.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

		String senha = user.getSenha();
		if (senha != null && !senha.startsWith("{")) {
			senha = "{noop}" + senha;
		}

		return org.springframework.security.core.userdetails.User.withUsername(user.getLogin())
			.password(senha)
			.roles("USER")
			.build();
	}
}
