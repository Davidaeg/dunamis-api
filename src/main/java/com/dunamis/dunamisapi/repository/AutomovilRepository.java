package com.dunamis.dunamisapi.repository;


import com.dunamis.dunamisapi.model.Automovil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutomovilRepository extends JpaRepository<Automovil, String> {
    List<Automovil> findByAutomovilActivoTrue();

    //Consultas
    Automovil findByPlaca(String Placa);

    List<Automovil> findByTipo(String tipo);

    @Query("SELECT a.tarifaBase FROM Automovil a WHERE a.tipo = :tipo")
    Double findTarifaBaseByTipo(@Param("tipo") String tipo);

    @Query("SELECT a.tarifaPorKilometro FROM Automovil a WHERE a.tipo = :tipo")
    Double findTarifaPorKilometroByTipo(@Param("tipo") String tipo);


}

