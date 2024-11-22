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

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nt.service.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl implements JWTService {

	private String secretKey = "";

	public JWTServiceImpl() {

		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String generateToken(String username) {

		Map<String, Object> claims = new HashMap<String, Object>();

		// TO generate token we will use Jwts class
		return Jwts.builder().claims().add(claims).subject(username).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30)).and().signWith(getKey()).compact();

	}

	private SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// Method to extract the username (subject) from the JWT token
	@Override
	public String extractUsernameByToken(String token) {
		// Call the extractClaim method with a function reference that retrieves the
		// 'subject' (usually the username) from the token
		return extractClaim(token, Claims::getSubject);
	}

	// Generic method to extract any claim from the JWT token, using a functional
	// interface to determine which claim to extract
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		// Extract all claims from the token by parsing it and validating its signature
		final Claims claims = extractAllClaims(token);

		// Apply the provided function (claimResolver) to the claims object to get the
		// specific claim (e.g., subject, expiration, etc.)
		return claimResolver.apply(claims);
	}

	// Method to extract all claims from the JWT token after parsing and validating
	// it
	private Claims extractAllClaims(String token) {
		// Using Jwts.parserBuilder() to create a new JWT parser that is more flexible
		// and up-to-date with the library's API
		return Jwts.parser() // Create a JWT parser builder
				.verifyWith(getKey()) // Set the key used to sign the token (important for verifying the token's
											// integrity)
				.build() // Finalize and build the parser
				.parseSignedClaims(token) // Parse the token and validate its signature. Returns a Jws<Claims> object
				.getPayload(); // Extracts and returns the claims (body) from the token after successful
							// parsing and validation
	}

	@Override
	public boolean validateToken(String token, UserDetails userDetails) {
final String userName = extractUsernameByToken(token);
return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpirationByToken(token).before(new Date());
	}

	private Date extractExpirationByToken(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration);
	}

}
