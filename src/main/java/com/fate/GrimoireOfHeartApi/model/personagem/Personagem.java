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
    private AtributosDeBatalha atributosDeBatalha;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAtributosSociais", referencedColumnName = "id")
    private AtributosSociais atributosSociais;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private int nivel;
    @Column
    private Long pontosDeBatalha = 0L;
    @Column
    private Long pontosSociais = 0L;
    public Personagem(String nomePersonagem, AtributosDeBatalha atributosDeBatalha) {
        this.nomePersonagem = nomePersonagem;
        this.atributosDeBatalha = atributosDeBatalha;
    }

    public Personagem(String nomePersonagem, AtributosDeBatalha atributosDeBatalha, AtributosSociais atributosSociais) {
        this.nomePersonagem = nomePersonagem;
        this.atributosDeBatalha = atributosDeBatalha;
        this.atributosSociais = atributosSociais;
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
    public void addPontoACharme(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosSociaisSuficientes(valor);
        atributosSociais.setCharme(valor);
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
