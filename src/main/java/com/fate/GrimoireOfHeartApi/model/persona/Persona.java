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
    public Persona(String nome) {
        this.nome = nome;
    }
    public Persona(String nome, ArrayList<TiposDeMagia> meusTiposDeMagia) {
        this.nome = nome;
        this.meusTiposDeMagia = meusTiposDeMagia;
    }
}
