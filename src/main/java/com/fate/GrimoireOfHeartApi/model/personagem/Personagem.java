package com.fate.GrimoireOfHeartApi.model.personagem;

import com.fate.GrimoireOfHeartApi.exceptions.NaoExistemPontosSuficientes;
import com.fate.GrimoireOfHeartApi.model.Klass.Klass;
import com.fate.GrimoireOfHeartApi.model.atributo.AtributosDeBatalha;
import com.fate.GrimoireOfHeartApi.model.atributo.AtributosSociais;
import com.fate.GrimoireOfHeartApi.model.persona.Persona;
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
    // Refatorar controller para passar respostas de Entity { "FEITO"} FALTA COMPLETAR O DE MAGIA
    // Tentar consumir a api pelo Front-End
    // ADICIONAR AS OUTRAS CLASSES DE PERSONAGEM E MELHORAR A KLASS
    // Criar bag e items
    // Criar Um objeto Habilidades sociais que guarde o Tier sociais e ganhe habilidades com determinado nivel de tier
    // Nas classes dividir o nome das habilidades com o que elas fazem
    // Conseguir uma forma de ter os danos da magia de forma apropriada

    @Column
    private String nomePersonagem;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAtributosDeBatalha", referencedColumnName = "id")
    private final AtributosDeBatalha atributosDeBatalha = new AtributosDeBatalha();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAtributosSociais", referencedColumnName = "id")
    private final AtributosSociais atributosSociais = new AtributosSociais();
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int nivel = 1;
    @Column
    private int vida = 0;
    @Column
    private Long energia = 0L;
    @Column
    private Long pontosDeBatalha = 0L;
    @Column
    private Long pontosSociais = 0L;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idPersona")
    private Persona persona;
    @Column
//    @JoinColumn(name = "idKlass", referencedColumnName = "id")
    private Klass klass;
    public Personagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
        calcularVida();
        calcularEnergia();
    }
    public Personagem(String nomePersonagem, Persona persona){
        this.nomePersonagem = nomePersonagem;
        this.persona = persona;
    }
    public Personagem(String nomePersonagem, Klass klass){
        this.nomePersonagem = nomePersonagem;
        this.klass = klass;
    }
    public void setNivel(int nivel){
        this.nivel = nivel;
        calcularVida();
        calcularEnergia();
    }
    private void calcularVida(){
        vida = (int) (25 + ((5 + atributosDeBatalha.getVitalidade()) * this.nivel));
    }
    private void calcularEnergia(){
        energia = atributosDeBatalha.getVitalidade() + (nivel / 2);
    }
    public void addPontoAFor(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosDeBatalhaSuficientes(valor);
        var valorASerAdicionado = atributosDeBatalha.getForca() + valor;
        atributosDeBatalha.setForca(valorASerAdicionado);
        pontosDeBatalha -= valor;
    }
    public void addPontoATec(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosDeBatalhaSuficientes(valor);
        var valorASerAdicionado = atributosDeBatalha.getTecnica() + valor;
        atributosDeBatalha.setTecnica(valorASerAdicionado);
        pontosDeBatalha -= valor;
    }
    public void addPontoAVit(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosDeBatalhaSuficientes(valor);
        var valorASerAdicionado = atributosDeBatalha.getVitalidade() + valor;
        atributosDeBatalha.setVitalidade(valorASerAdicionado);
        pontosDeBatalha -= valor;
        calcularVida();
        calcularEnergia();
    }
    public void addPontoAAgi(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosDeBatalhaSuficientes(valor);
        var valorASerAdicionado = atributosDeBatalha.getAgilidade() + valor;
        atributosDeBatalha.setAgilidade(valorASerAdicionado);
        pontosDeBatalha -= valor;
    }
    public void addPontoAMag(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosDeBatalhaSuficientes(valor);
        var valorASerAdicionado = atributosDeBatalha.getMagia() + valor;
        atributosDeBatalha.setMagia(valorASerAdicionado);
        pontosDeBatalha -= valor;
    }
    public void addPontoASor(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosDeBatalhaSuficientes(valor);
        var valorASerAdicionado = atributosDeBatalha.getSorte() + valor;
        atributosDeBatalha.setSorte(valorASerAdicionado);
        pontosDeBatalha -= valor;
    }
    public void addPontoAConhecimento(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosSociaisSuficientes(valor);
        var valorASerAdicionado = atributosSociais.getConhecimento() + valor;
        atributosSociais.setConhecimento(valorASerAdicionado);
        pontosSociais -= valor;
    }
    public void addPontoADisciplina(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosSociaisSuficientes(valor);
        var valorASerAdicionado = atributosSociais.getDisciplina() + valor;
        atributosSociais.setDisciplina(valorASerAdicionado);
        pontosSociais -= valor;
    }
    public void addPontoAEmpatia(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosSociaisSuficientes(valor);
        var valorASerAdicionado = atributosSociais.getEmpatia() + valor;
        atributosSociais.setEmpatia(valorASerAdicionado);
        pontosSociais -= valor;
    }
    public void addPontoACharme(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosSociaisSuficientes(valor);
        var valorASerAdicionado = atributosSociais.getCharme() + valor;
        atributosSociais.setCharme(valorASerAdicionado);
        pontosSociais -= valor;
    }
    public void addPontoAExpressao(Long valor) throws NaoExistemPontosSuficientes{
        verificacaoSeExistemPontosSociaisSuficientes(valor);
        var valorASerAdicionado = atributosSociais.getExpressao() + valor;
        atributosSociais.setExpressao(valorASerAdicionado);
        pontosSociais -= valor;
    }
    public void addPontoACoragem(Long valor) throws NaoExistemPontosSuficientes {
        verificacaoSeExistemPontosSociaisSuficientes(valor);
        var valorASerAdicionado = atributosSociais.getCoragem() + valor;
        atributosSociais.setCoragem(valorASerAdicionado);
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
