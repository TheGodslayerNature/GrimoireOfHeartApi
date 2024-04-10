package com.fate.GrimoireOfHeartApi.model.atributo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public record Atributo(String nome, int ponto,@Id @GeneratedValue(strategy = GenerationType.AUTO) int id) implements Serializable {
}
