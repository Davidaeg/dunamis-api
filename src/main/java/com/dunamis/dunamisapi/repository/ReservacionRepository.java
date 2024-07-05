package com.dunamis.dunamisapi.repository;

import com.dunamis.dunamisapi.model.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservacionRepository extends JpaRepository<Reservacion, Integer> {
}
