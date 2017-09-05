package com.springproject.clientes.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
	
	@GetMapping("/")
	public String toLogin() {
		return "redirect:/login";
	}
	
	@RequestMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
		
		if (user != null) {
			return "redirect:/clientes";
		}

		return "login";
	}

}
