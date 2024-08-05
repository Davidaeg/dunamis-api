package com.dunamis.dunamisapi.service;

import com.dunamis.dunamisapi.exception.UserException;
import com.dunamis.dunamisapi.model.Usuario;

public interface UserService {
    public Usuario registerUser(Usuario user) throws UserException;
    public Usuario loginUser() throws UserException;
}
