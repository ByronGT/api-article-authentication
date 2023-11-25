package com.company.intecap.apibooks.response;

public class ArticuloResponseRest extends ResponseRest { // ResponseRest: contiene toda la estructura de  la metada la respuesta de la api


    private ArticuloResponse articuloResponse = new ArticuloResponse(); // devolvera una lista de libros en formato json con la estructura de la clase LibroResponse y la metadata de la clase ResponseRest

    public ArticuloResponse getArticuloResponse() {
        return articuloResponse;
    }

    public void setArticuloResponse(ArticuloResponse articuloResponse) {
        this.articuloResponse = articuloResponse;
    }

}
