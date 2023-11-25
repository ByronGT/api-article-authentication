package com.company.intecap.apiarticulo.service;

import com.company.intecap.apiarticulo.model.Articulo;
import com.company.intecap.apiarticulo.model.dao.IArticuloDao;
import com.company.intecap.apiarticulo.response.ArticuloResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service //anotacion para indicar que es un servicio
public class ArticuloServiceImpl implements IArticuloService {

    private static final Logger log = Logger.getLogger(ArticuloServiceImpl.class.getName());

    @Autowired //@Autowired: inyecta el repositorio de articulos para poder utilizarlo en este servicio
    private IArticuloDao articuloDao; //variable de tipo IArticuloDao para acceder a los metodos de la interface IArticuloDao

    @Override
    @Transactional(readOnly = true) //anotacion para indicar que es un metodo de solo lectura
    public ResponseEntity<ArticuloResponseRest> buscarArticulos() {
        log.info("inicio metodo buscarArticulos");

        ArticuloResponseRest response = new ArticuloResponseRest(); //creacion de un objeto de tipo ArticuloResponseRest

        try {
            List<Articulo> listArticulos = (List<Articulo>) articuloDao.findAll(); //creacion de una lista de articulos para guardar los articulos que se encuentren en la base de datos
            response.getArticuloResponse().setArticulos(listArticulos); //se asigna la lista de articulos al objeto de tipo ArticuloResponseRest
            response.setMetadata("Respuesta exitosa", "200", "Lista de Articulos"); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest
        } catch (Exception e) {
            log.severe("Error en el metodo buscarArticulos: " + e.getMessage()); //se muestra un mensaje de error en caso de que ocurra una excepcion
            e.getStackTrace();
            response.setMetadata("Respuesta no exitosa", "500", "Error al consultar la lista de articulos"); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest

            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 500

        }
        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 200
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorId(Long id) {
        log.info("inicio metodo buscarArticuloPorId");

        ArticuloResponseRest response = new ArticuloResponseRest(); //creacion de un objeto de tipo ArticuloResponseRest
        List<Articulo> list = new ArrayList<>();

        try {
            Optional<Articulo> articulo = articuloDao.findById(id); //se busca un articulo por su id
            if (articulo.isPresent()) {
                list.add(articulo.get()); //se agrega el articulo a la lista
                response.getArticuloResponse().setArticulos(list); //se asigna la lista de articulos al objeto de tipo ArticuloResponseRest
                response.setMetadata("Respuesta exitosa", "200", "Articulo encontrado"); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest
            } else {
                log.severe("No se encontro el articulo con el Id: " + id); //se muestra un mensaje de error en caso de que no se encuentre el articulo
                response.setMetadata("Respuesta no exitosa", "404", "No se encontro el articulo con el Id: " + id); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 404
            }
        } catch (Exception e) {
            log.severe("Errror al buscar el articulo : " + e.getMessage());
            e.getStackTrace();
            response.setMetadata("Error al buscar el articulo", "500", "Error al consultar el articulo"); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 500
        }

        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 200
    }

    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> crear(Articulo articulo) {
        log.info("Iniciando el metodo metodo crear()  ");

        ArticuloResponseRest response = new ArticuloResponseRest(); //creacion de un objeto de tipo ArticuloResponseRest
        List<Articulo> list = new ArrayList<>();

        try {
            Articulo articuloGuardado = articuloDao.save(articulo); //se guarda el articulo en la base de datos
            if (articuloGuardado != null) {
                list.add(articuloGuardado); //se agrega el articulo a la lista
                response.getArticuloResponse().setArticulos(list); //se asigna la lista de articulos al objeto de tipo ArticuloResponseRest
                response.setMetadata("Respuesta exitosa", "200", "Articulo creado"); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest
            } else {
                log.severe("No se pudo guardar el articulo"); //se muestra un mensaje de error en caso de que no se pueda guardar el articulo
                response.setMetadata("Error al guardar el articulo", "500", "No se pudo guardar el articulo"); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 500
            }

        } catch (Exception e) {
            log.severe("Error al guardar el articulo: " + e.getMessage()); //se muestra un mensaje de error en caso de que ocurra una excepcion
            e.getStackTrace();
            response.setMetadata("Error al guardar el articulo", "500", "Error al guardar el articulo"); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 500
        }

        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 200
    }

    @Override
    @Transactional
    //commmit y rollback: si ocurre un error en la transaccion se hace un rollback y si no se hace un commit
    public ResponseEntity<ArticuloResponseRest> actualizar(Articulo articulo, Long id) {
        log.info("Iniciando el metodo actualizar()  ");

        ArticuloResponseRest response = new ArticuloResponseRest(); //creacion de un objeto de tipo ArticuloResponseRest
        List<Articulo> list = new ArrayList<>();

        try {
            Optional<Articulo> articuloBuscado = articuloDao.findById(id); //se busca un articulo por su id

            if (articuloBuscado.isPresent()) {
                articuloBuscado.get().setNombre(articulo.getNombre()); //se asigna el nombre del articulo que se quiere actualizar
                articuloBuscado.get().setPrecio(articulo.getPrecio()); //se asigna la descripcion del articulo que se quiere actualizar

                Articulo articuloActualizado = articuloDao.save(articuloBuscado.get()); //se actualiza el articulo en la base de datos

                if (articuloActualizado != null) { // si el articulo se actualizo correctamente
                    list.add(articuloActualizado); //se agrega el articulo a la lista
                    response.getArticuloResponse().setArticulos(list); //se asigna la lista de articulos al objeto de tipo ArticuloResponseRest
                    response.setMetadata("Respuesta exitosa", "200", "Articulo actualizado"); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRes
                } else {
                    log.severe("No se pudo actualizar el articulo"); //se muestra un mensaje de error en caso de que no se pueda actualizar el articulo
                    response.setMetadata("Error al actualizar el articulo", "500", "No se pudo actualizar el articulo"); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest
                    return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 500
                }
            } else {
                log.severe("No se encontro el articulo con el Id: " + id); //se muestra un mensaje de error en caso de que no se encuentre el articulo
                response.setMetadata("Respuesta no exitosa", "404", "No se encontro el articulo con el Id: " + id); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 404
            }


        } catch (Exception e) {
            log.severe("Error al actualizar el articulo: " + e.getMessage()); //se muestra un mensaje de error en caso de que ocurra una excepcion
            e.getStackTrace();
            response.setMetadata("Error al actualizar el articulo", "500", "Error al actualizar el articulo"); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 500
        }

        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 200
    }

    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> eliminar(Long id) {
        log.info("Iniciando el metodo eliminar()  ");

        ArticuloResponseRest response = new ArticuloResponseRest(); //creacion de un objeto de tipo ArticuloResponseRest

        try {

            Optional<Articulo> articuloBuscado = articuloDao.findById(id); //se busca un articulo por su id

            if (articuloBuscado.isPresent()) {
                articuloDao.delete(articuloBuscado.get()); //se elimina el articulo de la base de datos
                response.setMetadata("Respuesta exitosa", "200", "Articulo eliminado"); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest
            } else {
                log.severe("No se encontro el articulo con el Id: " + id); //se muestra un mensaje de error en caso de que no se encuentre el articulo
                response.setMetadata("Respuesta no exitosa", "404", "No se encontro el articulo con el Id: " + id); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest
                return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.NOT_FOUND); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 404
            }

        } catch (Exception e) {
            log.severe("Error al eliminar el articulo: " + e.getMessage()); //se muestra un mensaje de error en caso de que ocurra una excepcion
            e.getStackTrace();
            response.setMetadata("Error al eliminar el articulo", "500", "Error al eliminar el articulo"); //se asigna el mensaje de respuesta al objeto de tipo ArticuloResponseRest
            return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 500

        }

        return new ResponseEntity<ArticuloResponseRest>(response, HttpStatus.OK); //se retorna un objeto de tipo ResponseEntity con el objeto de tipo ArticuloResponseRest y el codigo de estado 200
    }

}
