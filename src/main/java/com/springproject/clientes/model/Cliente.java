package com.springproject.clientes.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "varchar(40)")
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@Column(columnDefinition = "varchar(40)")
	@NotBlank(message = "Endereço é obrigatório")
	private String endereco;
	
	@NotBlank(message = "Estado é obrigatório")
	private String estado;
	
	@Column(columnDefinition = "varchar(40)")
	@NotBlank(message = "Cidade é obrigatório")
	private String cidade;
	
	@Column(columnDefinition = "varchar(9)")
	@NotBlank(message = "CEP é obrigatório")
	private String cep;
	
	@Column(unique = true, columnDefinition = "varchar(20)")
	@NotBlank(message = "E-mail é obrigatório")
	@Email(message = "E-mail inválido") 
	private String email;
	
	@Column(name = "data_nas", columnDefinition = "date")
	@NotNull(message = "Data de nascimento inválida")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNas = LocalDate.of(1990, Month.JANUARY, 1);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNas() {
		return dataNas;
	}

	public void setDataNas(LocalDate dataNas) {
		this.dataNas = dataNas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
