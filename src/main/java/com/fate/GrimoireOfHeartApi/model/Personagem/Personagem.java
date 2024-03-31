package com.fate.GrimoireOfHeartApi.model.Personagem;

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
    private String nomeJogador;
    private String nomePersonagem;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
}
