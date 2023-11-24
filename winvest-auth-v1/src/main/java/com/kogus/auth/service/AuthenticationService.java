package com.kogus.auth.service;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kogus.auth.dto.AuthenticationRequest;
import com.kogus.auth.dto.AuthenticationResponse;
import com.kogus.auth.dto.RegisterRequest;
import com.kogus.auth.entity.Token;
import com.kogus.auth.entity.TokenType;
import com.kogus.auth.entity.User;
import com.kogus.auth.exceptions.UsernameInUseException;
import com.kogus.auth.repository.TokenRepository;
import com.kogus.auth.repository.UserRepository;
import com.kogus.dto.UserDto;
import com.kogus.events.EventManager;
import com.kogus.events.eventmodels.users.UserLoginEvent;
import com.kogus.events.eventmodels.users.UserRegisterEvent;
import com.kogus.utils.ReflectionUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = ReflectionUtils.cast(request, User.class);

        //Optional<User> findByEmail = repository.findByEmail(user.getEmail());
        Optional<User> findByUsername = repository.findByUsername(user.getUsername());
        //if (findByEmail.isPresent()) throw new EmailInUseException("Email kullanılıyor.");
        if (findByUsername.isPresent()) throw new UsernameInUseException("Kullanıcı adı kullanılıyor.");

        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        UserDto registerUserDto = ReflectionUtils.cast(savedUser, UserDto.class);
        EventManager.dispatchEvent(UserRegisterEvent.class, new UserRegisterEvent(registerUserDto, new Date()));
        
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                	.refreshtoken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        UserDto userDto = ReflectionUtils.cast(user, UserDto.class);
        EventManager.dispatchEvent(UserLoginEvent.class, new UserLoginEvent(userDto, new Date()));
        
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                	.refreshtoken(refreshToken)
                .build();
    }
    
    
    private void saveUserToken(User user, String jwtToken) {
    	if(tokenRepository.existsTokenByUserId(user.getId())){
    		Token token = tokenRepository.findByUserId(user.getId());
        	token.setToken(jwtToken);
        	tokenRepository.save(token);
    	}else {
    		
    		var token = Token.builder()
    				.userId(user.getId())
    				.token(jwtToken)
    				.tokenType(TokenType.BEARER)
    				.build();
    		tokenRepository.save(token);
    	}
    	
      }

      
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	    final String refreshToken;
	    final String userName;
	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    refreshToken = authHeader.substring(7);
	    userName = jwtService.extractUsername(refreshToken);
	    if (userName != null) {
	      var user = this.repository.findByUsername(userName)
	              .orElseThrow();
	      if (jwtService.isTokenValid(refreshToken, user)) {
	        var accessToken = jwtService.generateToken(user);
	        
	        saveUserToken(user, accessToken);
	        var authResponse = AuthenticationResponse.builder()
	                .accessToken(accessToken)
	                	.refreshtoken(refreshToken)
	                .build();
	        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
	      }
	    }
	  }
		
	

}
