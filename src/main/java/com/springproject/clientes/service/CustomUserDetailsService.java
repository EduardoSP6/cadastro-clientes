package com.springproject.clientes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.springproject.clientes.model.Usuario;
import com.springproject.clientes.repository.UsuariosRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	// Classe que implementa UserDetailService para efetuar autenticacao
	
	@Autowired
	UsuariosRepository usuarios;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarios.findByLogin(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}

		// cria lista de autenticacao
		List<GrantedAuthority> AuthorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");

		// retorna um UserDetailsService com os dados do usuario criado
		return new org.springframework.security.core.userdetails
				.User(usuario.getLogin(), usuario.getSenha(), AuthorityListUser);
	}

}
