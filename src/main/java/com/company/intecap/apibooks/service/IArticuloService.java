package com.company.intecap.apibooks.service;

import com.company.intecap.apibooks.model.Articulo;
import com.company.intecap.apibooks.response.ArticuloResponseRest;
import org.springframework.http.ResponseEntity;

public interface IArticuloService {
    public ResponseEntity<ArticuloResponseRest> buscarArticulos();
    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorId(Long id);
    public ResponseEntity<ArticuloResponseRest> crear(Articulo articulo);
    public ResponseEntity<ArticuloResponseRest> actualizar(Articulo articulo, Long id);
    public ResponseEntity<ArticuloResponseRest> eliminar(Long id);
}
