package com.task.agrl.services;

import com.task.agrl.models.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
}
