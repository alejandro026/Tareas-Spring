package com.task.agrl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.agrl.models.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	List<Usuario> findByUsername(String username);
}
