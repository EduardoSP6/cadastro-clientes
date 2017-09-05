package com.springproject.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.clientes.model.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {

	public Cliente findByEmailEquals(String email);
}
