package com.company.intecap.apiarticulo.controller;

import com.company.intecap.apiarticulo.model.Fabricante;
import com.company.intecap.apiarticulo.response.FabricanteResponseRest;
import com.company.intecap.apiarticulo.service.IFabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1") // prefijo de la ruta  http://localhost:8080/api/v1
public class FabricanteRestController {

    @Autowired // Inyectamos el servicio de fabricantes para poder utilizarlo en este controlador REST
    private IFabricanteService fabricanteService;

    @GetMapping("/fabricantes") // localhost:8080/api/v1/fabricantes
    public ResponseEntity<FabricanteResponseRest> consultarFabricantes(){
        return fabricanteService.buscarFabricantes(); // Invocamos el metodo buscarFabricantes del servicio de fabricantes para obtener las info de la base de datos
    }

    @GetMapping("/fabricantes/{id}") // Indica que este metodo se encarga de recibir las peticiones GET a la ruta /v1/fabricantes/{id}
    public ResponseEntity<FabricanteResponseRest> consultarFabricanteId(@PathVariable  Long id){
        return fabricanteService.buscarFabricanteId(id); // Invocamos el metodo buscarFabricanteId del servicio de fabricantes para obtener la info de la base de datos
    }

    @PostMapping("/fabricantes") // Indica que este metodo se encarga de recibir las peticiones POST a la ruta /v1/fabricantes
    public ResponseEntity<FabricanteResponseRest> guardarFabricante(@RequestBody Fabricante request){
        return fabricanteService.crear(request); // Invocamos el metodo crear del servicio de fabricantes para guardar la info en la base de datos
    }

    @PutMapping("/fabricantes/{id}")
    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(@RequestBody Fabricante request, @PathVariable Long id) {
        return fabricanteService.actualizar(request, id);

    }
    @DeleteMapping("/fabricantes/{id}")
    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(@PathVariable Long id) {
        return fabricanteService.eliminar(id);
    }

}
