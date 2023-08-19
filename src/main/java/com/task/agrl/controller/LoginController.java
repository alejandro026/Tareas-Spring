package com.task.agrl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.agrl.auth.ConfigSecurity;
import com.task.agrl.jwt.JwtProvider;
import com.task.agrl.models.entity.Usuario;
import com.task.agrl.services.UsuarioService;
import com.task.agrl.utils.Mensaje;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	UsuarioService usuarioServiceImpl;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	ConfigSecurity configSecurity;
	
	@PostMapping("/login")
	public ResponseEntity<Mensaje> login(@RequestBody Usuario loginUsuario, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(new Mensaje("Usuario y/o contrseña son requeridos", ""));
		}
		
		Authentication authentication= configSecurity.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt= jwtProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new Mensaje("Exito", jwt));
		
	}
	
}
