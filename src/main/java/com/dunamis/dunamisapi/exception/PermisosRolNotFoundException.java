package com.dunamis.dunamisapi.exception;

import com.dunamis.dunamisapi.repository.PermisosRepository;

public class PermisosRolNotFoundException extends RuntimeException{
    public PermisosRolNotFoundException(int id){super ("El permiso rol con el id no existes" + id);}
}
