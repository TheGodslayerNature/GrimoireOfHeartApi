package com.fate.GrimoireOfHeartApi.model.persona;

import com.fate.GrimoireOfHeartApi.model.Magia.Magia;
import com.fate.GrimoireOfHeartApi.model.tiposdemagia.TiposDeMagia;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


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
    private String conviccao;
    @Column
    private String habilidadeNatural;
    @Column
    private ArrayList<TiposDeMagia> meusTiposDeMagia = new ArrayList<>();
    @Column
    private HashMap<String,String> interacoesDeMagias = criarInteracoesMagicas();
    @Column
    @OneToMany
    private Magia[] deckDeMagia = new Magia[16];
    @OneToOne(cascade = CascadeType.ALL)
    private BonusPoints bonusPoints = new BonusPoints();
    public Persona(String nome) {
        this.nome = nome;
    }
    public Persona(String nome, ArrayList<TiposDeMagia> meusTiposDeMagia) {
        this.nome = nome;
        this.meusTiposDeMagia = meusTiposDeMagia;
    }

    public Persona(String nome, ArrayList<TiposDeMagia> meusTiposDeMagia, String conviccao, String habilidadeNatural) {
        this.nome = nome;
        this.meusTiposDeMagia = meusTiposDeMagia;
        this.conviccao = conviccao;
        this.habilidadeNatural = habilidadeNatural;
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
        for (int i = 0; i < deckDeMagia.length; i++) {
            if (deckDeMagia[i] == null){
                deckDeMagia[i] = magia;
                break;
            }
        }
    }

    public void removerMagia(Magia magia) {
        for (int i = 0; i < deckDeMagia.length; i++) {
            if (deckDeMagia[i] == magia){
                deckDeMagia[i] = null;
                break;
            }
        }
    }

    public void atualizarMagia(Magia magia){
        for (int i = 0; i < deckDeMagia.length; i++) {
             if (!Objects.equals(deckDeMagia[i].getNome(), magia.getNome())){
                deckDeMagia[i] = magia;
                break;
            }
        }
    }

    public void addPontoAFor(Long valor){
        bonusPoints.addPontoAFor(valor);
    }
    public void addPontoATec(Long valor){
        bonusPoints.addPontoATec(valor);
    }
    public void addPontoAVit(Long valor){
        bonusPoints.addPontoAVit(valor);
    }
    public void addPontoAAgi(Long valor){
        bonusPoints.addPontoAAgi(valor);
    }
    public void addPontoAMag(Long valor){
        bonusPoints.addPontoAMag(valor);
    }
    public void addPontoASor(Long valor){
        bonusPoints.addPontoASor(valor);
    }
    public void addPontoAConhecimento(Long valor){
        bonusPoints.addPontoAConhecimento(valor);
    }
    public void addPontoADisciplina(Long valor){
        bonusPoints.addPontoADisciplina(valor);
    }
    public void addPontoAEmpatia(Long valor){
        bonusPoints.addPontoAEmpatia(valor);
    }
    public void addPontoACharme(Long valor){
        bonusPoints.addPontoACharme(valor);
    }
    public void addPontoAExpressao(Long valor){
        bonusPoints.addPontoAExpressao(valor);
    }
    public void addPontoACoragem(Long valor){
        bonusPoints.addPontoACoragem(valor);
    }

    public String printBonus(){
        return bonusPoints.printBonus();
    }
}
