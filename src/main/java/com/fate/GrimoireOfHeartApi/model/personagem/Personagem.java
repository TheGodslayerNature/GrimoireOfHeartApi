package com.fate.GrimoireOfHeartApi.model.personagem;

import com.fate.GrimoireOfHeartApi.model.atributo.Atributos;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity(name = "personagem")
@Table(name = "personagem")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Personagem implements Serializable {
    private String nomeJogador;
    private String nomePersonagem;
    private Atributos atributosDeBatalha;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    public Personagem(String nomeJogador, String nomePersonagem, Atributos atributosDeBatalha) {
        this.nomeJogador = nomeJogador;
        this.nomePersonagem = nomePersonagem;
        this.atributosDeBatalha = atributosDeBatalha;
    }

    public void adicionarPontos(String nomeDoAtributo, int valor){
        atributosDeBatalha.adicionarPontos(nomeDoAtributo, valor);
    }
}
