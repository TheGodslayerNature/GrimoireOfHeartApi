package com.fate.GrimoireOfHeartApi.model.tiposdemagia;

import lombok.Getter;

@Getter
public enum TiposDeMagia {
    Fisico("Físico", Constantes.DANO, "Utiliza a Força dos Usuários para causar dano massivo e efeitos versáteis."),
    Fogo("Fogo", Constantes.DANO, "Um destrutivo Tipo de dano focado em intenso dano em área, ao custo de \n" +
            "menor dano contra alvos individuais."),
    Gelo("Gelo",Constantes.DANO, "Um Tipo de dano capaz de esmagar alvos individuais com alto dano."),
    Vento("Gelo",Constantes.DANO,"Magias de Vento permitem que o Usuário escolha entre melhores chances \n" +
            "de acerto ou golpes Críticos melhores, oferecendo flexibilidade e alcance sólido. "),
    Raio("Raio", Constantes.DANO, "Magias de Raio são focadas ao redor do Status Choque, que deixa os alvos \n" +
            "vulneráveis para dano massivo e evita que eles esquivem."),
    Nuclear("Nuclear", Constantes.DANO, "Esse Tipo concede ao Usuário um medidor de Acúmulo Nuclear único, \n" +
            "que pode ser usado para liberar efeitos no campo de batalha ou uma explosão violenta."),
    Psicocinese("Psicocinese",Constantes.DANO,""),
    Luz("Luz",Constantes.DANO,"Magias Básicas de Luz causam dano alto contra alvos com PV alto, enquanto " +
            "as magias de Zênite oferecem efeitos em área e utilidade extra."),
    Trevas("Trevas",Constantes.DANO,""),
    Onipotente("Onipotente",Constantes.DANO,"");
    private final String nome;
    private final String categoria;
    private final String descricao;

    TiposDeMagia(String nome, String categoria, String descricao) {
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    private static class Constantes {
        public static final String DANO = "Dano";
    }
}
