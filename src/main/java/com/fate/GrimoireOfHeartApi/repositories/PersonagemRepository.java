package com.fate.GrimoireOfHeartApi.repositories;

import com.fate.GrimoireOfHeartApi.model.personagem.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem,Integer> {
}
