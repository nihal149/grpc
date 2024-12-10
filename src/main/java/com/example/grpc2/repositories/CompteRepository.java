package com.example.grpc2.repositories;


import com.example.grpc2.entities.Compte;
import org.springframework.data.jpa.repository. JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, String> {
    List<Compte> findByType(String type);
}