package com.company.intecap.apiarticulo.service;

import com.company.intecap.apiarticulo.model.Fabricante;
import com.company.intecap.apiarticulo.response.FabricanteResponseRest;
import org.springframework.http.ResponseEntity;

public interface IFabricanteService {

    public ResponseEntity<FabricanteResponseRest> buscarFabricantes();
    public  ResponseEntity<FabricanteResponseRest> buscarFabricanteId(Long id);
    public  ResponseEntity<FabricanteResponseRest> crear(Fabricante fabricante);
    public  ResponseEntity<FabricanteResponseRest> actualizar(Fabricante fabricante,Long id);
    public  ResponseEntity<FabricanteResponseRest> eliminar(Long id);
}
