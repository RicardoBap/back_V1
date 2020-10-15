package com.example.algamoney.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByEmail(String email);
	
	public Page<Usuario> findByNomeContaining(String nome, Pageable pageable);
}
