package com.dunamis.dunamisapi.repository;

import com.dunamis.dunamisapi.model.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservacionRepository extends JpaRepository<Reservacion, Integer> {


}


