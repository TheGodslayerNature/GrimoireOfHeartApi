package com.fate.GrimoireOfHeartApi.model.persona;

import com.fate.GrimoireOfHeartApi.model.tiposdemagia.TiposDeMagia;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


@Entity(name = "persona")
@Table(name = "persona")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Persona implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String nome;
    @Column
    private int nivel = 1;
    @Column
    private int pontosDeMagia = 6;
    @Column
    private ArrayList<TiposDeMagia> meusTiposDeMagia = new ArrayList<>();
    @Column
    private HashMap<String,String> interacoesDeMagias = criarInteracoesMagicas();
    public Persona(String nome) {
        this.nome = nome;
    }
    public Persona(String nome, ArrayList<TiposDeMagia> meusTiposDeMagia) {
        this.nome = nome;
        this.meusTiposDeMagia = meusTiposDeMagia;
    }

    public HashMap<String,String> criarInteracoesMagicas(){
        HashMap<String,String> interacoes = new HashMap<>();
        interacoes.put("Físico","Neutro");
        interacoes.put("Fogo","Neutro");
        interacoes.put("Luz", "Neutro");
        interacoes.put("Trevas","Neutro");
        return interacoes;
    }
    public void addResistencia(TiposDeMagia tiposDeMagia){
        if (meusTiposDeMagia.contains(tiposDeMagia))
            interacoesDeMagias.put(tiposDeMagia.getNome(),"Resiste");
    }

    public void addFraqueza(TiposDeMagia tiposDeMagia) {
        if (!meusTiposDeMagia.contains(tiposDeMagia))
            interacoesDeMagias.put(tiposDeMagia.getNome(),"Fraco");
    }
}
