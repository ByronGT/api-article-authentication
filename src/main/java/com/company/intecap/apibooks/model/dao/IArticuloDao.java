package com.company.intecap.apiarticulo.model.dao;

import com.company.intecap.apiarticulo.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticuloDao extends JpaRepository<Articulo, Long> {
}
