package com.company.intecap.apiarticulo.service;

import com.company.intecap.apiarticulo.model.Articulo;
import com.company.intecap.apiarticulo.response.ArticuloResponseRest;
import org.springframework.http.ResponseEntity;

public interface IArticuloService {
    public ResponseEntity<ArticuloResponseRest> buscarArticulos();
    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorId(Long id);
    public ResponseEntity<ArticuloResponseRest> crear(Articulo articulo);
    public ResponseEntity<ArticuloResponseRest> actualizar(Articulo articulo, Long id);
    public ResponseEntity<ArticuloResponseRest> eliminar(Long id);
}
