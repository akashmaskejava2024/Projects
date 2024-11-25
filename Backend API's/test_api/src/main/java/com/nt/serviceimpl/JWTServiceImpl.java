package com.nt.serviceimpl;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.aspectj.weaver.ast.And;
import org.hibernate.sql.DecodeCaseFragment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nt.service.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JWTServiceImpl implements JWTService{
	
	private String secretKey = "";
	
	
	// generating sercet key 
	
	
	public JWTServiceImpl() {
		
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
			// key is genereated into byte[] anf then string
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	// generating JWT TOken
	
	
	@Override
	public String generateJwtToken(String username) {
		Map<String, Object> claims = new HashMap<String, Object>();
		
		return Jwts.builder()
		 .claims(claims)
		 .subject(username)
		 .issuedAt(new Date(System.currentTimeMillis()))
		 .expiration(new Date(System.currentTimeMillis()+ 60 * 60 * 30) )
		 .signWith(getKey())
		 .compact();
		 
	}

	
	// suppllying signWith  secret key
	
	private SecretKey getKey() {

		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		// get decoded into byte[] from string
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	
	// get Username by token 

	@Override
	public String getUsernameByToken(String token) {
		
		String username = extractClaim(token , Claims::getSubject);
		return username;
	}

	 private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		//It takes an input of type Claims and produces an output of type T (generic type).

		final Claims allClaims = extractAllClaims(token);
		
		return claimResolver.apply(allClaims);
	}

	 private Claims extractAllClaims(String token) {
		 System.out.println(token);
		    try {
		    	  return Jwts.parser() // Create a JWT parser builder
		                  .verifyWith(getKey()) // Set the key used to sign the token (important for verifying the token's
		                                              // integrity)
		                  .build() // Finalize and build the parser
		                  .parseSignedClaims(token) // Parse the token and validate its signature. Returns a Jws<Claims> object
		                  .getPayload();
// Extract claims
		    } catch (io.jsonwebtoken.security.SecurityException | io.jsonwebtoken.MalformedJwtException e) {
		        // Thrown if the token's signature is invalid or malformed
		        throw new RuntimeException("Invalid JWT signature or token is malformed.", e);
		    } catch (io.jsonwebtoken.ExpiredJwtException e) {
		        // Thrown if the token has expired
		        throw new RuntimeException("JWT token is expired.", e);
		    } catch (io.jsonwebtoken.UnsupportedJwtException e) {
		        // Thrown if the token is unsupported
		        throw new RuntimeException("JWT token is unsupported.", e);
		    } catch (IllegalArgumentException e) {
		        // Thrown if the token is null or empty
		        throw new RuntimeException("JWT claims string is empty.", e);
		    }
		    
		}

	
	
	// validation part 
	

	@Override
	public boolean validateToken(String token, UserDetails userDetails) {
		
		final String username = getUsernameByToken(token);

		
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}


	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return expirationTimeByToken(token).before(new Date());
	}


	private Date expirationTimeByToken(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token,Claims::getExpiration);
	}

	

}
