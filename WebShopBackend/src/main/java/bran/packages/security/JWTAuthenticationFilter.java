package bran.packages.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import bran.packages.user.entity.User;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	 private final AuthenticationManager authenticationManager;

	    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
	    }

	    @Override
	    public Authentication attemptAuthentication(
	            HttpServletRequest request, HttpServletResponse response
	    ) throws AuthenticationException {
	        try {
	            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
	            return authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>())
	            );
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    @Override
	    protected void successfulAuthentication(
	            HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult
	    ) {

	        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
	        String roles = authResult.getAuthorities().toString();
	        
	        String token = JWT.create()
	                .withSubject(username)
	                .withClaim("Role", roles)
	                .withExpiresAt(new Date(System.currentTimeMillis() + JWTConstants.EXPIRATION_TIME))
	                .sign(Algorithm.HMAC512(JWTConstants.SECRET_KEY));

	        
	        response.addHeader(JWTConstants.AUTHORIZATION_HEADER, JWTConstants.TOKEN_PREFIX + token);
	    }

}
