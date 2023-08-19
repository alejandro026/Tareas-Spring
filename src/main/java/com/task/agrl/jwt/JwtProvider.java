package com.task.agrl.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.task.agrl.utils.LoginUsuario;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtProvider {
	
	private String secret = "estaclavesirveparafirmarnuestrotokenjwtestaclavesirveparafirmarnuestrotokenjwt";
	private int expiration = 36000;

	public String generateToken(Authentication authentication) {
		LoginUsuario usuarioPrincipal = (LoginUsuario) authentication.getPrincipal();
		List<String> roles = usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		return Jwts.builder().setSubject(usuarioPrincipal.getUsername()).claim("roles", roles)
				.claim("idUsuario", usuarioPrincipal.getId()).claim("nombre", usuarioPrincipal.getNombre())
				.setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + expiration * 180))
				.signWith(getSecret(secret), SignatureAlgorithm.HS256).compact();
	}

	private Key getSecret(String secret) {
		byte[] secretBytes = Decoders.BASE64URL.decode(secret);
		return Keys.hmacShaKeyFor(secretBytes);
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			log.error("token mal formado");
		} catch (UnsupportedJwtException e) {
			log.error("token no soportado");
		} catch (ExpiredJwtException e) {
			log.error("token expirado");
		} catch (IllegalArgumentException e) {
			log.error("token vacio");
		} 

		
		return false;
	}

	public String getNombreUsuarioFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token).getBody()
				.getSubject();
	}
	

}
