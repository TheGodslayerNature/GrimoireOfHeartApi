package com.fate.GrimoireOfHeartApi.model.atributo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Entity
public class AtributosDeBatalha implements Serializable {
    @Id
    private int id;
    private final Atributo forca = new Atributo("FOR", 0);
    private final Atributo tec = new Atributo("TEC", 0);
    private final Atributo vit = new Atributo("VIT", 0);
    private final Atributo mag = new Atributo("MAG", 0);
    private final Atributo agi = new Atributo("AGI", 0);
    private final Atributo sor = new Atributo("SOR", 0);
    private final LinkedHashMap<String, Integer> meusAtributos = new LinkedHashMap<>();
    public AtributosDeBatalha() {
        meusAtributos.put(forca.getNome(), forca.getPonto());
        meusAtributos.put(tec.getNome(), tec.getPonto());
        meusAtributos.put(vit.getNome(), vit.getPonto());
        meusAtributos.put(mag.getNome(), mag.getPonto());
        meusAtributos.put(agi.getNome(), agi.getPonto());
        meusAtributos.put(sor.getNome(), sor.getPonto());
    }

    public List<String> nomeDosAtributosDeBatalha() {
        return meusAtributos.entrySet().stream().map( att -> String.format("%s: %o", att.getKey(), att.getValue())).toList();
    }
    public void adicionarPontos(String nomeDoAtributo, int valor){
        if (meusAtributos.containsKey(nomeDoAtributo)) meusAtributos.put(nomeDoAtributo, valor);
    }
}
