package com.fate.GrimoireOfHeartApi.model.persona;

import com.fate.GrimoireOfHeartApi.model.Magia.Magia;
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
    @Column
    @OneToMany
    private Magia[] deck = new Magia[16];
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
        interacoes.put("Gelo","Neutro");
        interacoes.put("Vento","Neutro");
        interacoes.put("Raio","Neutro");
        interacoes.put("Nuclear","Neutro");
        interacoes.put("Psicocinese","Neutro");
        interacoes.put("Luz", "Neutro");
        interacoes.put("Trevas","Neutro");
        interacoes.put("Onipotente","Neutro");
        interacoes.put("Status","Neutro");
        interacoes.put("Intel","Neutro");
        return interacoes;
    }
    public void addResistencia(TiposDeMagia tiposDeMagia){
        interacoesDeMagias.put(tiposDeMagia.getNome(),"Resiste");
        /* Adicionava resistência apenas se tivesse o tipo
        if (meusTiposDeMagia.contains(tiposDeMagia))
            interacoesDeMagias.put(tiposDeMagia.getNome(),"Resiste");
         */
    }

    public void addFraqueza(TiposDeMagia tiposDeMagia) {
        interacoesDeMagias.put(tiposDeMagia.getNome(),"Fraco");
        /*adicionava fraqueza apenas se não tivesse o tipo
        if (!meusTiposDeMagia.contains(tiposDeMagia))
            interacoesDeMagias.put(tiposDeMagia.getNome(),"Fraco");

         */
    }
    public void addMagia(Magia magia) {
        deck[0] = magia;
    }
}
