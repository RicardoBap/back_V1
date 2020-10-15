package com.example.algamoney.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.model.Permissao;
import com.example.algamoney.api.repository.PermissaoRepository;

@RestController
@RequestMapping("/permissao")
public class PermissaoResource {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@GetMapping
	public List<Permissao> getPermissao() {
		return permissaoRepository.findAll();
	}

}
