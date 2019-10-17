package com.pavan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
public class WelcomeController {

	@GetMapping("/")
	@ApiIgnore
	public String getGreating() {
		return "redirect:/swagger-ui.html";
	}
}
