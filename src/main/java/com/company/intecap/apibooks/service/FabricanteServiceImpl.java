package com.company.intecap.apiarticulo.service;

import com.company.intecap.apiarticulo.model.Fabricante;
import com.company.intecap.apiarticulo.model.dao.IFabricanteDao;
import com.company.intecap.apiarticulo.response.FabricanteResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class FabricanteServiceImpl implements IFabricanteService {

    private static final Logger log = Logger.getLogger(FabricanteServiceImpl.class.getName());

    @Autowired //Inyeccion de dependencias
    private IFabricanteDao fabricanteDao; //Instancia de la interface IFabricanteDao Jpa

    @Override
    @Transactional(readOnly = true) //Metodo para buscar todas las fabricantes de la base de datos
    public ResponseEntity<FabricanteResponseRest> buscarFabricantes() {
        log.info("Inicio metodo buscarFabricantes()");

        FabricanteResponseRest response = new FabricanteResponseRest();

        try {
            List<Fabricante> listaFabricantes = (List<Fabricante>) fabricanteDao.findAll();
            response.getFabricanteResponse().setFabricantes(listaFabricantes);
            response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");

        } catch (Exception e) {
            log.info("Error al consultar fabricantes: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta no ok", "500", "Error al consultar fabricantes");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //Retorna un error 500
        }

        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK); //Retorna un error 200
    }

    @Override
    @Transactional(readOnly = true) //Metodo para buscar una fabricante por su id
    public ResponseEntity<FabricanteResponseRest> buscarFabricanteId(Long id) {
        log.info("Inicio metodo buscarFabricanteId()");

        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> listaFabricantes = new ArrayList<>();

        try {
            Optional<Fabricante> fabricante = fabricanteDao.findById(id);

            if (fabricante.isPresent()) {
                listaFabricantes.add(fabricante.get());
                response.getFabricanteResponse().setFabricantes(listaFabricantes);
                response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta no ok", "404", "Fabricante no encontrado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND); //Retorna un error 404
            }
        } catch (Exception e) {
            log.info("Error al consultar fabricante: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta no ok", "500", "Error al consultar fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //Retorna un error 500
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK); //Retorna un error 200
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> crear(Fabricante fabricante) {
        log.info("Inicio metodo crear()");

        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> listaFabricantes = new ArrayList<>();

        try {
            Fabricante fabricanteGuardada = fabricanteDao.save(fabricante);
            if (fabricanteGuardada != null) {
                listaFabricantes.add(fabricanteGuardada);
                response.getFabricanteResponse().setFabricantes(listaFabricantes);
                response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
            } else {
                log.info("Error al guardar fabricante");
                response.setMetadata("Respuesta no ok", "404", "Fabricante no encontrada");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND); //Retorna un error 404
            }
        } catch (Exception e) {
            log.severe("Error al guardar fabricante: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta no ok", "500", "Error al guardar fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //Retorna un error 500

        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK); //Retorna un error 200
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> actualizar(Fabricante fabricante,Long id) {
        log.info("Inicio metodo actualizar()");

        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> listaFabricantes = new ArrayList<>();

        try {
            Optional<Fabricante> fabricanteBuscada = fabricanteDao.findById(id);

            if (fabricanteBuscada.isPresent()) {
                fabricanteBuscada.get().setNombre(fabricante.getNombre());
                Fabricante fabricanteActualizada = fabricanteDao.save(fabricanteBuscada.get());

                if (fabricanteActualizada != null) { //Si la fabricante se actualiza correctamente
                    listaFabricantes.add(fabricanteActualizada);
                    response.getFabricanteResponse().setFabricantes(listaFabricantes);
                    response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
                } else {
                    log.info("Error al actualizar fabricante");
                    response.setMetadata("Respuesta no ok", "404", "Fabricante no encontrada");
                    return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND); //Retorna un error 404
                }
            } else {
                log.info("Error al actualizar fabricante");
                response.setMetadata("Respuesta no ok", "404", "Fabricante no encontrada");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND); //Retorna un error 404
            }

        } catch (Exception e) {
            log.severe("Error al actualizar fabricante: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta no ok", "500", "Error al actualizar fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //Retorna un error 50
        }

        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK); //Retorna un error 200
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> eliminar(Long id) {
        log.info("Inicio metodo eliminar()");

        FabricanteResponseRest response = new FabricanteResponseRest();

        try {
            Optional<Fabricante> fabricanteBuscada = fabricanteDao.findById(id);

            if (fabricanteBuscada.isPresent()) {
                fabricanteDao.deleteById(id);
                response.setMetadata("Respuesta ok", "200", "Respuesta exitosa");
            } else {
                log.info("Error al eliminar fabricante " + id);
                response.setMetadata("Respuesta no ok", "404", "Fabricante no encontrada " + id);
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND); //Retorna un error 404
            }

        } catch (Exception e) {
            log.severe("Error al eliminar fabricante: " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta no ok", "500", "Error al eliminar fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //Retorna un error 500

        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK); //Retorna un error 200
    }
}
