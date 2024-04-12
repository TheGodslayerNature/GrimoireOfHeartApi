package com.fate.GrimoireOfHeartApi.model.personagem;

import com.fate.GrimoireOfHeartApi.model.atributo.AtributosDeBatalha;
import com.fate.GrimoireOfHeartApi.model.atributo.AtributosSociais;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

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
    private Long pontosDeBatalha;
    @Column
    private Long pontosSociais;
    public Personagem(String nomePersonagem, AtributosDeBatalha atributosDeBatalha) {
        this.nomePersonagem = nomePersonagem;
        this.atributosDeBatalha = atributosDeBatalha;
    }

    public Personagem(String nomePersonagem, AtributosDeBatalha atributosDeBatalha, AtributosSociais atributosSociais) {
        this.nomePersonagem = nomePersonagem;
        this.atributosDeBatalha = atributosDeBatalha;
        this.atributosSociais = atributosSociais;
    }
    public void addPontoAFor(Long valor){
        if (pontosDeBatalha >= valor ) atributosDeBatalha.setForca(valor);
        atributosDeBatalha.setForca(valor);
    }
    public void addPontoATec(Long valor){
        atributosDeBatalha.setTecnica(valor);
    }
    public void addPontoAVit(Long valor){
        atributosDeBatalha.setVitalidade(valor);
    }
    public void addPontoAAgi(Long valor){
        if (pontosDeBatalha < 1) return;
        atributosDeBatalha.setAgilidade(valor);
    }
    public void addPontoAMag(Long valor){
        atributosDeBatalha.setMagia(valor);
    }
    public void addPontoASor(Long valor){
        atributosDeBatalha.setSorte(valor);
    }
//    public void adicionarPontoAAtributoSocial(String nomeDoAtributo, int valor){
//        atributosSociais.adicionarPontos(nomeDoAtributo, valor);
//    }

    public String printAtributosDeBatalha(){
        return atributosDeBatalha.printAtributosDeBatalha();
    }
}
