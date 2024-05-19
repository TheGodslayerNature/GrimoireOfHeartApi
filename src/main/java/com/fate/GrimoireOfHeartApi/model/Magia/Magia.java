package com.fate.GrimoireOfHeartApi.model.Magia;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "Magia")
@Table(name = "Magia")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Magia implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    int tier;
    String Nome;
    String Categorias;
    String Tempo;
    String Alcance;
    String Duracao;
    String Efeito;
    String Descricao;
    public Magia(int tier, String nome, String categorias, String tempo, String alcance, String duracao, String efeito, String descricao) {
        this.tier = tier;
        Nome = nome;
        Categorias = categorias;
        Tempo = tempo;
        Alcance = alcance;
        Duracao = duracao;
        Efeito = efeito;
        Descricao = descricao;
    }
}
