package com.fate.GrimoireOfHeartApi.repositories;

import com.fate.GrimoireOfHeartApi.model.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
