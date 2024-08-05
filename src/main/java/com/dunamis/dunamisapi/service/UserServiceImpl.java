package com.dunamis.dunamisapi.service;

import com.dunamis.dunamisapi.model.Usuario;
import com.dunamis.dunamisapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UsuarioRepository userRepo;

    @Override
    public Usuario registerUser(Usuario user) {

        return userRepo.save(user);
    }

    @Override
    public Usuario loginUser() {

        SecurityContext sc  = SecurityContextHolder.getContext();

        Authentication auth  = sc.getAuthentication();

        String userName = auth.getName();

        return userRepo.findByEmail(userName);

    }
}
