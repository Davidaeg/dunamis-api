package com.dunamis.dunamisapi.security;

import java.util.ArrayList;
import java.util.Collection;

import com.dunamis.dunamisapi.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

    private Usuario user;

    public MyUserDetails(Usuario user) {
        this.user= user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getRol().getNombre());
        authorities.add(sga);
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user.getContrasenna();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return user.getNombre();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
