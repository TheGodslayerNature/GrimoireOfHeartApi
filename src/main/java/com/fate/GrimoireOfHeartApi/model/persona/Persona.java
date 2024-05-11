package com.fate.GrimoireOfHeartApi.model.persona;

import com.fate.GrimoireOfHeartApi.model.tiposdemagia.TiposDeMagia;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

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
    private ArrayList<String> interacoesDeMagias = new ArrayList<>();
    public Persona(String nome) {
        this.nome = nome;
    }
    public Persona(String nome, ArrayList<TiposDeMagia> meusTiposDeMagia) {
        this.nome = nome;
        this.meusTiposDeMagia = meusTiposDeMagia;
    }
    public void addResistencia(TiposDeMagia tiposDeMagia){
        if (meusTiposDeMagia.contains(tiposDeMagia)){
            interacoesDeMagias.add(tiposDeMagia.getNome() + ": Resiste");
        }
        else {
            interacoesDeMagias.add("Você não possui o tipo da magia");
        }
    }

    public void addFraqueza(TiposDeMagia tiposDeMagia) {
        if (meusTiposDeMagia.contains(tiposDeMagia)){
            interacoesDeMagias.add("Você não pode ser fraco a um tipo seu");
        }
        else {
            interacoesDeMagias.add(tiposDeMagia.getNome() + ": Fraco");
        }
    }
}
