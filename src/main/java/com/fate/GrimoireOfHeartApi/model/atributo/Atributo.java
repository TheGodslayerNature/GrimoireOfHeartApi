package com.fate.GrimoireOfHeartApi.model.atributo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Atributo {
    private String name;
    private int ponto;

    public Atributo(String name, int ponto) {
        this.name = name;
        this.ponto = ponto;
    }

}
