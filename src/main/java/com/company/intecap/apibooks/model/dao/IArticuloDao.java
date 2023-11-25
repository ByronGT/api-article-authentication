package com.company.intecap.apibooks.model.dao;

import com.company.intecap.apibooks.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticuloDao extends JpaRepository<Articulo, Long> {
}
