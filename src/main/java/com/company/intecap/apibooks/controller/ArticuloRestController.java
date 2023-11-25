package com.company.intecap.apiarticulo.controller;

import com.company.intecap.apiarticulo.model.Articulo;
import com.company.intecap.apiarticulo.response.ArticuloResponseRest;
import com.company.intecap.apiarticulo.service.IArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1") // @RequestMapping: prefijo de la ruta de la API REST  http://localhost:8083/api/v1
public class ArticuloRestController {

    @Autowired // @Autowired: inyecta el servicio de articulos para poder utilizarlo en este controlador REST
    private IArticuloService articuloService; // IArticuloService: interface que contiene los metodos para acceder a los datos de la tabla articulo de la base de datos (CRUD)

    @GetMapping("/articulos") // localhost:8083/api/v1/articulos
    // @GetMapping: indica que este metodo se encarga de recibir las peticiones GET a la ruta /v1/articulos
    public ResponseEntity<ArticuloResponseRest> buscarArticulos() { //http://localhost:8083/api/v1/articulos
        return articuloService.buscarArticulos(); // Retornamos la respuesta al cliente
    }

    @GetMapping("/articulos/{id}")
    // @GetMapping: indica que este metodo se encarga de recibir las peticiones GET a la ruta /v1/articulos/{id}
    public ResponseEntity<ArticuloResponseRest> consultarPorId(@PathVariable Long id) {  //  http://localhost:8083/api/v1/articulos/1
        return articuloService.buscarArticuloPorId(id);
    }

    @PostMapping("/articulos")
    // @PostMapping: indica que este metodo se encarga de recibir las peticiones POST a la ruta /v1/articulos
    public ResponseEntity<ArticuloResponseRest> guardarArticulo(@RequestBody Articulo request) {
        return articuloService.crear(request);
    }

    @PutMapping("/articulos/{id}")
    // @PutMapping: indica que este metodo se encarga de recibir las peticiones PUT a la ruta /v1/articulos
    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(@RequestBody Articulo request, @PathVariable Long id) {
        return articuloService.actualizar(request, id);

    }

    @DeleteMapping("/articulos/{id}")
    // @DeleteMapping: indica que este metodo se encarga de recibir las peticiones DELETE a la ruta /v1/articulos/{id}
    public ResponseEntity<ArticuloResponseRest> eliminarArticulo(@PathVariable Long id) {
        return articuloService.eliminar(id);
    }


}
