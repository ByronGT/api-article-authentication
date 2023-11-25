package com.company.intecap.apiarticulo.response;

import com.company.intecap.apiarticulo.model.Articulo;

import java.util.List;

public class ArticuloResponse {
    private List<Articulo> articulos;

    public List<Articulo> getArticulos() {
        // getLibros: obtiene la información de la respuesta.
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        // setLibros: establece la información de la respuesta.
        this.articulos = articulos;
    }
}
