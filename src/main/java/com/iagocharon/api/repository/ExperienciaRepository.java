package com.iagocharon.api.repository;

import com.iagocharon.api.entity.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Integer> {

    Optional<Experiencia> findById(int id);

    List<Experiencia> findAllByOrderByIdAsc();


    boolean existsById(int id);

}
