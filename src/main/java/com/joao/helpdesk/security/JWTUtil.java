package com.joao.helpdesk.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.expiration}")
	private Long expiration;

	@Value("${jwt.secret}")
	private String secret;

	/*
	 * METODO PARA GERARA O TOKEN ATRAVEIS DO JWT
	 */
	public String generateToken(String email) {
		//contar o tempo

		return Jwts.builder().setSubject(email).setExpiration(new Date(System.currentTimeMillis() + expiration))																									

				.signWith(SignatureAlgorithm.HS512, secret.getBytes())// VAI EMABRALHAR
				.compact();
	}
}
