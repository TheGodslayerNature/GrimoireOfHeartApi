package com.fate.GrimoireOfHeartApi.model.atributo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Atributos implements Serializable {
    @Id
    private int id;
    Atributo forca = new Atributo("FOR", 0);
    Atributo tec = new Atributo("TEC", 0);
    Atributo vit = new Atributo("VIT", 0);
    Atributo mag = new Atributo("MAG", 0);
    Atributo agi = new Atributo("AGI", 0);
    Atributo sor = new Atributo("SOR", 0);
    private ArrayList<Atributo> myAtributos = new ArrayList<>();
    public Atributos() {
        myAtributos.add(forca);
        myAtributos.add(tec);
        myAtributos.add(vit);
        myAtributos.add(mag);
        myAtributos.add(agi);
        myAtributos.add(sor);
    }

    public List<String> nomesDosAtributos() {
        ArrayList<String> atributosDeBatalha = new ArrayList<>();
        for (Atributo att: myAtributos) {
            atributosDeBatalha.add(att.getName() + ": " + att.getPonto());
        }
        return atributosDeBatalha;
    }
    public void adicionarPontos(String nomeDoAtributo, int valor){
        for (Atributo att: myAtributos) {
            if (att.getName().equals(nomeDoAtributo)) att.setPonto(valor);
        }
    }
}
