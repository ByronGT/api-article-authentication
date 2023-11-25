package com.company.intecap.apiarticulo.response;

import com.company.intecap.apiarticulo.model.Fabricante;

import java.util.List;

public class FabricanteResponse {

    private List<Fabricante> fabricantes;

    public List<Fabricante> getFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }
}

