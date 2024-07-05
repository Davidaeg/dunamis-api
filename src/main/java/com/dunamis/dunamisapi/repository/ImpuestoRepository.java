package com.dunamis.dunamisapi.repository;

import com.dunamis.dunamisapi.model.Impuesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImpuestoRepository extends JpaRepository<Impuesto, Integer> {
}