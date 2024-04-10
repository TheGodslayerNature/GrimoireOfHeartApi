package com.fate.GrimoireOfHeartApi.model.atributo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.*;

@Entity
public class AtributosSociais implements Serializable {
    @Id
    private int id;
    private Atributo conhecimento = new Atributo("CONHECIMENTO", 0, 1);
    private Atributo disciplina = new Atributo("DISCIPLINA", 0, 2);
    private Atributo empatia = new Atributo("EMPATIA", 0, 3);
    private Atributo charme = new Atributo("CHARME", 0, 4);
    private Atributo expressao = new Atributo("EXPRESSAO", 0, 5);
    private Atributo coragem = new Atributo("CORAGEM", 0, 6);
    private final LinkedHashMap<String,Integer> meusAtributos = new LinkedHashMap<>();
    public AtributosSociais() {
        this.meusAtributos.put(conhecimento.nome(), conhecimento.ponto());
        this.meusAtributos.put(disciplina.nome(), disciplina.ponto());
        this.meusAtributos.put(empatia.nome(), empatia.ponto());
        this.meusAtributos.put(charme.nome(), charme.ponto());
        this.meusAtributos.put(expressao.nome(), expressao.ponto());
        this.meusAtributos.put(coragem.nome(), coragem.ponto());
    }

    public List<String> nomeDosAtributosSociais(){
        return  meusAtributos.entrySet().stream().map( att -> String.format("%s: %o", att.getKey(), att.getValue())).toList();
    }
    public void adicionarPontos(String nomeDoAtributo, int ponto){
        if (meusAtributos.containsKey(nomeDoAtributo)) meusAtributos.put(nomeDoAtributo, ponto);
    }
}
