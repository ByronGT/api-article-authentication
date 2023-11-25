package com.company.intecap.apiarticulo.model.dao;

import com.company.intecap.apiarticulo.model.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFabricanteDao extends JpaRepository<Fabricante, Long> {
}
