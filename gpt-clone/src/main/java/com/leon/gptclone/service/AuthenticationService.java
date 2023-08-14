package com.leon.gptclone.service;

import com.leon.gptclone.model.CurrentUser;
import com.leon.gptclone.model.response.LoginResponseDTO;
import com.leon.gptclone.model.Role;
import com.leon.gptclone.model.User;
import com.leon.gptclone.model.response.Response;
import com.leon.gptclone.repository.RoleRepository;
import com.leon.gptclone.repository.UserRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Currency;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<Object> register(String username, String password){
        String encondedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findRoleByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        userRepository.save(new User(0, username, encondedPassword, authorities));
        return new ResponseEntity<>(new Response("0000", "Success"), HttpStatus.CREATED);
    }

    public ResponseEntity<Object> login(String username, String password){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new ResponseEntity<>(new LoginResponseDTO(token, "0000", "Success"), HttpStatus.OK);
        }
        catch (AuthenticationException e){
            return new ResponseEntity<>(new Response("0002", "Wrong password or username"), HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<Object> logout(){
        if (CurrentUser.user == null)
            return new ResponseEntity<>(new Response("0005", "You have not login yet"), HttpStatus.BAD_REQUEST);
        CurrentUser.user = null;
        return new ResponseEntity<>(new Response("0000", "Success"), HttpStatus.OK);
    }


}
