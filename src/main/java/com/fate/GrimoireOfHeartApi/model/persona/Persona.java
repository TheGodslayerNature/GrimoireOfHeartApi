package com.fate.GrimoireOfHeartApi.model.persona;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

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
    public Persona(String nome) {
        this.nome = nome;
    }
}
