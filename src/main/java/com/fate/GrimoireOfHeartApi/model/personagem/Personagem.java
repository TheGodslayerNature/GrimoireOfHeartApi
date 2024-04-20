package com.fate.GrimoireOfHeartApi.model.personagem;

import com.fate.GrimoireOfHeartApi.exceptions.NaoExistemPontosSuficientes;
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
    private String nomePersonagem;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAtributosDeBatalha", referencedColumnName = "id")
    private final AtributosDeBatalha atributosDeBatalha = new AtributosDeBatalha();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAtributosSociais", referencedColumnName = "id")
    private final AtributosSociais atributosSociais = new AtributosSociais();
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private int nivel = 1;
    @Column
    private int vida;
    @Column
    private Long pontosDeBatalha = 0L;
    @Column
    private Long pontosSociais = 0L;
    public Personagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public void addPontoAFor(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosDeBatalhaSuficientes(valor);
        atributosDeBatalha.setForca(valor);
        pontosDeBatalha -= valor;
    }
    public void addPontoATec(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosDeBatalhaSuficientes(valor);
        atributosDeBatalha.setTecnica(valor);
        pontosDeBatalha -= valor;
    }
    public void addPontoAVit(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosDeBatalhaSuficientes(valor);
        atributosDeBatalha.setVitalidade(valor);
        pontosDeBatalha -= valor;
    }
    public void addPontoAAgi(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosDeBatalhaSuficientes(valor);
        atributosDeBatalha.setAgilidade(valor);
        pontosDeBatalha -= valor;
    }
    public void addPontoAMag(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosDeBatalhaSuficientes(valor);
        atributosDeBatalha.setMagia(valor);
        pontosDeBatalha -= valor;
    }
    public void addPontoASor(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosDeBatalhaSuficientes(valor);
        atributosDeBatalha.setSorte(valor);
        pontosDeBatalha -= valor;
    }
    public void addPontoAConhecimento(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosSociaisSuficientes(valor);
        atributosSociais.setConhecimento(valor);
        pontosSociais -= valor;
    }
    public void addPontoADisciplina(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosSociaisSuficientes(valor);
        atributosSociais.setDisciplina(valor);
        pontosSociais -= valor;
    }
    public void addPontoAEmpatia(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosSociaisSuficientes(valor);
        atributosSociais.setEmpatia(valor);
        pontosSociais -= valor;
    }
    public void addPontoACharme(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosSociaisSuficientes(valor);
        atributosSociais.setCharme(valor);
        pontosSociais -= valor;
    }
    public void addPontoAExpressao(Long valor) throws NaoExistemPontosSuficientes{
        verificacaoSeExistemPontosSociaisSuficientes(valor);
        atributosSociais.setExpressao(valor);
        pontosSociais -= valor;
    }
    public void addPontoACoragem(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosSociaisSuficientes(valor);
        atributosSociais.setCoragem(valor);
        pontosSociais -= valor;
    }
    private void verificacaoSeExistemPontosDeBatalhaSuficientes(Long valor) throws NaoExistemPontosSuficientes {
        if (pontosDeBatalha < valor)   {
            throw new NaoExistemPontosSuficientes("Não existem pontos suficientes");
        }
    }
    private void verificacaoSeExistemPontosSociaisSuficientes(Long valor) throws NaoExistemPontosSuficientes {
        if (pontosSociais < valor)   {
            throw new NaoExistemPontosSuficientes("Não existem pontos suficientes");
        }
    }
    public String printAtributosDeBatalha(){
        return atributosDeBatalha.printAtributosDeBatalha();
    }
    public String printAtributosSociais(){
        return atributosSociais.printAtributosSociais();
    }
}
