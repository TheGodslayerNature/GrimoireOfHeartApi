package com.fate.GrimoireOfHeartApi.repositories;

import com.fate.GrimoireOfHeartApi.model.Magia.Magia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagiaRepository extends JpaRepository<Magia,Integer> {
}
