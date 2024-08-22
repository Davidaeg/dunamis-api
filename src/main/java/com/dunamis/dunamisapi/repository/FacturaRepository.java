package com.dunamis.dunamisapi.repository;

import com.dunamis.dunamisapi.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {


}
