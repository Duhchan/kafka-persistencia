package com.pcfactory.ventas.repository;

import com.pcfactory.ventas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// con esto ya esta con una comunicacion con jpa uniendo la clase de bentas model con el repository
public interface VentasRepository extends JpaRepository <Venta, Long> {

}
