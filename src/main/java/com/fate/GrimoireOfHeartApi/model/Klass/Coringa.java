package com.fate.GrimoireOfHeartApi.model.Klass;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Coringa implements Klass {
    @Column
    private String nome;
    @Column
    private String versatilidadeInigualavel = "Versatilidade";
    @Column
    private String borboletaSocial = "Borboleta";
    @Column
    private String tudoSeraRevelado = "Tudo";
    @Column
    private String convidadoEspecial = "Convidado";
    @Column
    @OneToMany
    private List<String> habilidades = new ArrayList<>();
    public Coringa(String nome){
        this.nome = nome;
        addHabilidades();
    }

    private void addHabilidades(){
        habilidades.add(versatilidadeInigualavel);
        habilidades.add(borboletaSocial);
        habilidades.add(tudoSeraRevelado);
        habilidades.add(convidadoEspecial);
    }

    @Override
    public String getNomeDaClasse() {
        return nome;
    }

    @Override
    public List<String> getHabilidades() {
        return habilidades;
    }
}
