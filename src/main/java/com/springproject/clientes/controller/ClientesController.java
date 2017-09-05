package com.springproject.clientes.controller;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springproject.clientes.model.Cliente;
import com.springproject.clientes.repository.ClientesRepository;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	private ClientesRepository clientes;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("cliente/lista-clientes");
		modelAndView.addObject("clientes", clientes.findAll());
		return modelAndView;
	}

	@GetMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		// exibe formulario de cadastro
		ModelAndView mv = new ModelAndView("cliente/cadastro-cliente");
		mv.addObject(cliente);
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		// Validacoes

		// Valida idade do cliente baseado na data de nascimento
		LocalDate data = cliente.getDataNas();
		long idade = Period.between(data, LocalDate.now()).getYears();
		
		if (idade < 16) {
			result.addError(new ObjectError("dataNas", "Cliente não deve ter idade inferior a 16 anos"));
		};
		
		// Valida se há um cliente cadastrado com mesmo endereço de email		
		if (clientes.findByEmailEquals(cliente.getEmail()) != null) {
			result.addError(new ObjectError("email", "Já existe cliente cadastrado com este e-mail"));
		}
				
		// se houver erros redireciona para o metodo novo reutilizando o objeto corrente
		// exibindo as mensagens de erro
		if (result.hasErrors()) {
			return novo(cliente);
		};
	
		// Sava o cliente no banco de dados
		clientes.save(cliente);
		
		ModelAndView mv = new ModelAndView("redirect:/clientes/novo");
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		// editar cliente
		Cliente cliente = clientes.findOne(id);
		return novo(cliente);
	}
	
	@DeleteMapping(value = "/{id}")
	public String apagar(@PathVariable Long id, RedirectAttributes attributes) {
		// apaga registro do banco de dados
		clientes.delete(id);
		attributes.addFlashAttribute("mensagem", "Cliente removido com sucesso!");
		return "redirect:/clientes";
	}
}
