package com.task.agrl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.agrl.models.entity.Usuario;
import com.task.agrl.repository.IUsuarioRepository;
import com.task.agrl.utils.LoginUsuario;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	
	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username).get(0);
	}

	@Override
	@Transactional(readOnly= true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username).get(0);
		if(usuario==null) {
			throw new UsernameNotFoundException("Error: no existe el usuario"+ username);
		}
		
		log.info("Usuario: "+ usuario.getUsername());
		
		return LoginUsuario.build(usuario);
	}

}
