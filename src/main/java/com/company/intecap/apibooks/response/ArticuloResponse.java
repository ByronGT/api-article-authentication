package com.company.intecap.apibooks.response;

import com.company.intecap.apibooks.model.Articulo;

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
