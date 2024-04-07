package com.fate.GrimoireOfHeartApi.model.atributo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

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
        meusAtributos.put(forca.nome(), forca.ponto());
        meusAtributos.put(tec.nome(), tec.ponto());
        meusAtributos.put(vit.nome(), vit.ponto());
        meusAtributos.put(mag.nome(), mag.ponto());
        meusAtributos.put(agi.nome(), agi.ponto());
        meusAtributos.put(sor.nome(), sor.ponto());
    }

    public List<String> nomeDosAtributosDeBatalha() {
        return meusAtributos.entrySet().stream().map( att -> String.format("%s: %o", att.getKey(), att.getValue())).toList();
    }
    public void adicionarPontos(String nomeDoAtributo, int valor){
        if (meusAtributos.containsKey(nomeDoAtributo)) meusAtributos.put(nomeDoAtributo, valor);
    }
}
