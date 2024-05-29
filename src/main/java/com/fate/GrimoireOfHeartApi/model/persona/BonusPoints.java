package com.fate.GrimoireOfHeartApi.model.persona;

import com.fate.GrimoireOfHeartApi.model.atributo.AtributosDeBatalha;
import com.fate.GrimoireOfHeartApi.model.atributo.AtributosSociais;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Data
@Setter
@Getter
public class BonusPoints implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAtributosDeBatalha", referencedColumnName = "id")
    private final AtributosDeBatalha atributosDeBatalha = new AtributosDeBatalha();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAtributosSociais", referencedColumnName = "id")
    private final AtributosSociais atributosSociais = new AtributosSociais();

    public void addPontoAFor(Long valor){
        var valorASerAdicionado = atributosDeBatalha.getForca() + valor;
        atributosDeBatalha.setForca(valorASerAdicionado);
    }
    public void addPontoATec(Long valor){
        var valorASerAdicionado = atributosDeBatalha.getTecnica() + valor;
        atributosDeBatalha.setTecnica(valorASerAdicionado);
    }
    public void addPontoAVit(Long valor){
        var valorASerAdicionado = atributosDeBatalha.getVitalidade() + valor;
        atributosDeBatalha.setVitalidade(valorASerAdicionado);
    }
    public void addPontoAAgi(Long valor){
        var valorASerAdicionado = atributosDeBatalha.getAgilidade() + valor;
        atributosDeBatalha.setAgilidade(valorASerAdicionado);
    }
    public void addPontoAMag(Long valor){
        var valorASerAdicionado = atributosDeBatalha.getMagia() + valor;
        atributosDeBatalha.setMagia(valorASerAdicionado);
    }
    public void addPontoASor(Long valor){
        var valorASerAdicionado = atributosDeBatalha.getSorte() + valor;
        atributosDeBatalha.setSorte(valorASerAdicionado);
    }
    public void addPontoAConhecimento(Long valor){
        var valorASerAdicionado = atributosSociais.getConhecimento() + valor;
        atributosSociais.setConhecimento(valorASerAdicionado);
    }
    public void addPontoADisciplina(Long valor){
        var valorASerAdicionado = atributosSociais.getDisciplina() + valor;
        atributosSociais.setDisciplina(valorASerAdicionado);
    }
    public void addPontoAEmpatia(Long valor){
        var valorASerAdicionado = atributosSociais.getEmpatia() + valor;
        atributosSociais.setEmpatia(valorASerAdicionado);
    }
    public void addPontoACharme(Long valor){
        var valorASerAdicionado = atributosSociais.getCharme() + valor;
        atributosSociais.setCharme(valorASerAdicionado);
    }
    public void addPontoAExpressao(Long valor){
        var valorASerAdicionado = atributosSociais.getExpressao() + valor;
        atributosSociais.setExpressao(valorASerAdicionado);
    }
    public void addPontoACoragem(Long valor){
        var valorASerAdicionado = atributosSociais.getCoragem() + valor;
        atributosSociais.setCoragem(valorASerAdicionado);
    }

    public String printBonus(){
        return atributosDeBatalha.printAtributosDeBatalha() + " || " + atributosSociais.printAtributosSociais();
    }
}
