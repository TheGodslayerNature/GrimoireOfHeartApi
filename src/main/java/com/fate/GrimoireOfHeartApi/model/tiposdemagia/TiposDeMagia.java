package com.fate.GrimoireOfHeartApi.model.tiposdemagia;

import lombok.Getter;

@Getter
public enum TiposDeMagia {
    Fisico("Físico", Constantes.DANO, "Utiliza a Força dos Usuários para causar dano massivo e efeitos versáteis."),
    Fogo("Fogo", Constantes.DANO, "Um destrutivo Tipo de dano focado em intenso dano em área, ao custo de " + "menor dano contra alvos individuais."),
    Gelo("Gelo",Constantes.DANO, "Um Tipo de dano capaz de esmagar alvos individuais com alto dano."),
    Vento("Gelo",Constantes.DANO,"Magias de Vento permitem que o Usuário escolha entre melhores chances " + "de acerto ou golpes Críticos melhores, oferecendo flexibilidade e alcance sólido. "),
    Raio("Raio", Constantes.DANO, "Magias de Raio são focadas ao redor do Status Choque, que deixa os alvos " + "vulneráveis para dano massivo e evita que eles esquivem."),
    Nuclear("Nuclear", Constantes.DANO, "Esse Tipo concede ao Usuário um medidor de Acúmulo Nuclear único, " + "que pode ser usado para liberar efeitos no campo de batalha ou uma explosão violenta."),
    Psicocinese("Psicocinese",Constantes.DANO,""),
    Luz("Luz",Constantes.DANO,"Magias Básicas de Luz causam dano alto contra alvos com PV alto, enquanto " +
            "as magias de Zênite oferecem efeitos em área e utilidade extra."),
    Trevas("Trevas",Constantes.DANO,"Magias de Trevas contém a poderosa categoria Morte, capaz de derrotar " + "inimigos enfraquecidos, ou acumular poderosas Maldições que ativam no turno do alvo."),
    Onipotente("Onipotente",Constantes.DANO," Conjure poderosas magias de múltiplos turnos que ignoram defesas."),
    Buff("Buff",Constantes.SUPORTE,"Reforce aliados e temporariamente aumente suas Habilidades."),
    Debuff("Debuff",Constantes.SUPORTE,"Enfraqueça inimigos e amplie suas fraquezas."),
    Cura("Cura",Constantes.SUPORTE,"Recupere PV, cure Status e traga aliados de volta das portas da morte."),
    Status("Status",Constantes.CONTROLE,"Aplique efeitos como Veneno e Medo, que podem limitar as capacidades " + "inimigas ou fortalecer estratégias dos seus aliados."),
    Intel("Intel",Constantes.CONTROLE,"Analise inimigos e controle o campo de batalha com efeitos únicos.");
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
        public static final String SUPORTE = "Suporte";
        public static final String CONTROLE = "Controle";
    }
}
