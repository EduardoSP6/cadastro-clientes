package com.springproject.clientes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.clientes.model.Usuario;
import com.springproject.clientes.repository.UsuariosRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired 
	UsuariosRepository usuarios;
	
	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		// exibe formulario de cadastro
		ModelAndView mv = new ModelAndView("usuario/cadastro-usuario");
		mv.addObject(usuario);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result) {
	
		// Valida se há um usuario cadastrado com mesmo login		
		if (usuarios.findByLogin(usuario.getLogin()) != null) {
			result.addError(new ObjectError("login", "Nome de usuário já existe"));
		};
				
		// se houver erros redireciona para o metodo novo reutilizando o objeto corrente
		// exibindo as mensagens de erro
		if (result.hasErrors()) {
			return novo(usuario);
		};
		
		// aplica criptografia no campo senha
		String senha = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senha);	
		
		// salva o objeto no banco de dados
		usuarios.save(usuario);
		
		ModelAndView mv = new ModelAndView("redirect:/login");
		return mv;
	}
	
}
