package com.fate.GrimoireOfHeartApi.model.atributo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.*;

@Entity
public class AtributosSociais implements Serializable {
    @Id
    private int id;
    private Atributo conhecimento = new Atributo("CONHECIMENTO", 0);
    private Atributo disciplina = new Atributo("DISCIPLINA", 0);
    private Atributo empatia = new Atributo("EMPATIA", 0);
    private Atributo charme = new Atributo("CHARME", 0);
    private Atributo expressao = new Atributo("EXPRESSAO", 0);
    private Atributo coragem = new Atributo("CORAGEM", 0);
    private final LinkedHashMap<String,Integer> meusAtributos = new LinkedHashMap<>();
    public AtributosSociais() {
        this.meusAtributos.put(conhecimento.getNome(), conhecimento.getPonto());
        this.meusAtributos.put(disciplina.getNome(), disciplina.getPonto());
        this.meusAtributos.put(empatia.getNome(), empatia.getPonto());
        this.meusAtributos.put(charme.getNome(), charme.getPonto());
        this.meusAtributos.put(expressao.getNome(), expressao.getPonto());
        this.meusAtributos.put(coragem.getNome(), coragem.getPonto());
    }

    public List<String> nomeDosAtributosSociais(){
        return  meusAtributos.entrySet().stream().map( att -> String.format("%s: %o", att.getKey(), att.getValue())).toList();
    }
    public void adicionarPontos(String nomeDoAtributo, int ponto){
        if (meusAtributos.containsKey(nomeDoAtributo)) meusAtributos.put(nomeDoAtributo, ponto);
    }
}
