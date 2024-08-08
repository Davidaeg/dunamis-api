package com.dunamis.dunamisapi.repository;


import com.dunamis.dunamisapi.model.Automovil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutomovilRepository extends JpaRepository<Automovil, String> {
    List<Automovil> findByAutomovilActivoTrue();
}
