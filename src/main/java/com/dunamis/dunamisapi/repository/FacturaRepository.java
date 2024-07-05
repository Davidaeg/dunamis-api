package com.dunamis.dunamisapi.repository;

import com.dunamis.dunamisapi.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}
