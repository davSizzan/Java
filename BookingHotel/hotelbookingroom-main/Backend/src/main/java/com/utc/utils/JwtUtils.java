package com.utc.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
	@Value("${utc.app.jwtSecretKey}")
	private String JWT_SECRET_KEY;
	
	@Value("${utc.app.jwtExpirationMs}")
	private int JWT_EXPIRATION_MS;
	
	public String generateJwtToken(Authentication auth) {
		
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_MS))
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
				.compact();
	}
	
	public boolean validateJwtToken(String token) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token);
			return true;
		}catch(Exception ex) {
			throw ex;
		}
		//return false;
	}
	
	public String getUsernameByToken(String token) {
		return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}
	
}
