package com.dunamis.dunamisapi.security;

import com.dunamis.dunamisapi.model.Usuario;
import com.dunamis.dunamisapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user  = userRepository.findByEmail(username);

        if(user!=null)
        {
            return new MyUserDetails(user);
        }

        throw new UsernameNotFoundException("user not found with this username : "+username);
    }

}
