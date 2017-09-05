package com.springproject.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.clientes.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Long>{

	Usuario findByLogin(String login);
	
}
