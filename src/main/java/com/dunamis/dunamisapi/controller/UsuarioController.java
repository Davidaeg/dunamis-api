package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.exception.UserException;
import com.dunamis.dunamisapi.model.Usuario;
import com.dunamis.dunamisapi.repository.UsuarioRepository;
import com.dunamis.dunamisapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UserService userService;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/app/signup")
    public ResponseEntity<Usuario> registerUser(@Validated @RequestBody Usuario user) throws UserException
    {
        user.setContrasenna(passwordEncoder.encode(user.getContrasenna()));

        Usuario p = userService.registerUser(user);

        return new ResponseEntity<Usuario>(p, HttpStatus.CREATED);
    }

    @GetMapping("/signIn")
    public ResponseEntity<Usuario> getLoggedInCustomerDetailsHandler(Authentication auth) throws BadCredentialsException{

        Usuario user= userRepository.findByEmail(auth.getName());

        if(user!=null)
        {
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }

        throw new BadCredentialsException("Invalid Username or password");


    }

    @GetMapping("/logged-in/user")
    public ResponseEntity<String> LoginUser() throws UserException
    {
        Usuario user =  userService.loginUser();

        String message = "Welcome to Shimbhu's Website  : " +user.getNombre();

        return new ResponseEntity<String>(message,HttpStatus.OK);
    }
}
