package com.fate.GrimoireOfHeartApi.model.personagem;

import com.fate.GrimoireOfHeartApi.model.atributo.AtributosDeBatalha;
import com.fate.GrimoireOfHeartApi.model.atributo.AtributosSociais;
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
    @Column
    private String nomeJogador;
    @Column
    private String nomePersonagem;
    @Column
    private AtributosDeBatalha atributosDeBatalha;
    @Column
    private AtributosSociais atributosSociais;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    public Personagem(String nomeJogador, String nomePersonagem, AtributosDeBatalha atributosDeBatalha) {
        this.nomeJogador = nomeJogador;
        this.nomePersonagem = nomePersonagem;
        this.atributosDeBatalha = atributosDeBatalha;
    }

    public Personagem(String nomeJogador, String nomePersonagem) {
        this.nomeJogador = nomeJogador;
        this.nomePersonagem = nomePersonagem;
        this.atributosDeBatalha = atributosDeBatalha;
    }

    public Personagem(String nomeJogador, String nomePersonagem, AtributosDeBatalha atributosDeBatalha, AtributosSociais atributosSociais) {
        this.nomeJogador = nomeJogador;
        this.nomePersonagem = nomePersonagem;
        this.atributosDeBatalha = atributosDeBatalha;
        this.atributosSociais = atributosSociais;
    }

    public void adicionarPontoAAtributoDeBatalha(String nomeDoAtributo, int valor){
        atributosDeBatalha.adicionarPontos(nomeDoAtributo, valor);
    }
    public void adicionarPontoAAtributoSocial(String nomeDoAtributo, int valor){
        atributosSociais.adicionarPontos(nomeDoAtributo, valor);
    }
}
