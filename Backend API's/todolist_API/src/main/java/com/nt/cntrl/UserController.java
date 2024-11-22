
Certainly! Here’s a step-by-step guide on how to implement JWT-based session management with authentication in a Java REST API (Spring Boot) and manage it from the frontend (React).


 Flow Overview


1. User Logs In (React): User provides credentials (username/email and password).
2. Backend (Spring Boot): The backend verifies credentials and generates a JWT token.
3. Frontend (React): The JWT token is stored securely (typically in `localStorage` or `sessionStorage`).
4. User Makes Requests (React): For every subsequent request, the JWT token is included in the `Authorization` header.
5. Backend (Spring Boot): The backend checks the validity of the JWT token and if valid, allows access to protected resources. Otherwise, it returns a `401 Unauthorized` response.


---


 Step 1: Backend (Spring Boot) Setup


 1.1 Set Up JWT Utility Class
This class handles creating, validating, and parsing the JWT tokens.


java
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTokenUtil {


    private String secretKey = "secret"; // Should be externalized (in application.properties)


    // Token expiration time (e.g., 1 hour)
    private long expirationTime = 1000 * 60 * 60;


    // Generate JWT Token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }


    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    // Extract username from token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }


    // Get all claims from token
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }


    // Validate the token
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (username.equals(extractedUsername) && !isTokenExpired(token));
    }


    // Check if the token has expired
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}



 1.2 Implement the JWT Filter


The JWT filter will intercept all incoming requests to check for the presence of the JWT token and validate it.


java
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil(); // Instantiate the JwtTokenUtil


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        // Get JWT from Authorization header
        String token = request.getHeader("Authorization");


        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix


            // Validate token
            if (jwtTokenUtil.validateToken(token, jwtTokenUtil.extractUsername(token))) {
                // If valid, set authentication in Spring Security context
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(jwtTokenUtil.extractUsername(token), null, new ArrayList<>());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }


        filterChain.doFilter(request, response); // Continue with the request
    }
}



 1.3 Register the JWT Filter in Security Configuration


You need to add the JWT filter into the Spring Security filter chain to ensure the JWT token is checked for every request.


java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/login", "/register").permitAll()  // Permit login and register
            .anyRequest().authenticated()  // Require authentication for other requests
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Add JWT filter before Spring Security's default filter


        return http.build();
    }
}



 1.4 Authentication Controller (Login)
Here’s a simple controller to handle user login and JWT generation.


java
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {


    private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();


    @PostMapping("/login")
    public String login(@RequestBody UserCredentials credentials) {
        // Verify username and password (authentication logic goes here)
        // For simplicity, assuming username and password are valid
       
        return jwtTokenUtil.generateToken(credentials.getUsername());  // Generate and return JWT
    }
}



In this example, after a successful login, the backend will generate a JWT and send it back to the frontend.


---


 Step 2: Frontend (React)


Now let’s implement how the React frontend interacts with the backend.


 2.1 Store the JWT Token


Once the user logs in, you need to store the JWT token securely. A common practice is to store it in `localStorage` or `sessionStorage`, though `localStorage` is more common because it persists across browser sessions.


Here’s how you can handle login and store the token in localStorage:


javascript
import React, { useState } from 'react';
import axios from 'axios';


const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');


    const handleLogin = async (e) => {
        e.preventDefault();


        try {
            const response = await axios.post('http://localhost:8080/login', {
                username,
                password
            });


            // Save JWT token to localStorage
            localStorage.setItem('authToken', response.data);  // Store JWT
            window.location.href = "/dashboard";  // Redirect to protected page
        } catch (error) {
            setError('Invalid username or password');
        }
    };


    return (
        <form onSubmit={handleLogin}>
            <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Username" required />
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" required />
            <button type="submit">Login</button>
            {error && <div>{error}</div>}
        </form>
    );
};


export default Login;



 2.2 Attach JWT to API Requests


After the JWT is stored, for every subsequent API request that needs authentication, you should attach the token to the `Authorization` header.


Here’s an example of how to send a request with the JWT token:


javascript
import axios from 'axios';


// Attach JWT token from localStorage to request headers
const api = axios.create({
    baseURL: 'http://localhost:8080/api/',
    headers: {
        Authorization: `Bearer ${localStorage.getItem('authToken')}`
    }
});


// Example GET request to a protected endpoint
const getProtectedData = async () => {
    try {
        const response = await api.get('/protected-resource');
        console.log(response.data);
    } catch (error) {
        if (error.response && error.response.status === 401) {
            console.log('Unauthorized! Token might be invalid or expired.');
        }
    }
};



This setup automatically adds the JWT token to every request made by `api` and allows access to protected resources if the token is valid.


 2.3 Handle Token Expiration


If the token is expired or invalid, the backend will return a `401 Unauthorized` error. You should handle this in the frontend:


javascript
import axios from 'axios';


// Intercept requests to handle token expiration
axios.interceptors.response.use(
    response => response,
    error => {
        if (error.response && error.response.status === 401) {
            // Token expired or invalid - redirect to login
            localStorage.removeItem('authToken');
            window.location.href = '/login';  // Redirect to login
        }
        return Promise.reject(error);
    }
);



---


 Step 3: Conclusion


 Backend (Spring Boot):
1. JWT Utility for generating and validating tokens.
2


. JWT Filter to authenticate requests by validating the JWT token.
3. Controller to handle login and token generation.


 Frontend (React):
1. Login Page: Accepts credentials and sends them to the backend to retrieve the JWT token.
2. Store JWT: Store the token securely in `localStorage` (or `sessionStorage`).
3. Authenticated API Requests: Attach the JWT to the `Authorization` header for every API request.


---


With this setup, you have a robust JWT-based session management system where the backend ensures security via token validation, and the frontend handles the storage and transmission of the token for subsequent requests.


