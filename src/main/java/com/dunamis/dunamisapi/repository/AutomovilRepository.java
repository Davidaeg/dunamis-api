package com.dunamis.dunamisapi.repository;


import com.dunamis.dunamisapi.model.Automovil;
import com.dunamis.dunamisapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomovilRepository extends JpaRepository<Automovil, String> {
    //Automovil findByPlaca(String placa);
}
