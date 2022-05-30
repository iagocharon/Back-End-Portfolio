package com.iagocharon.api.repository;

import com.iagocharon.api.entity.HardSkill;
import com.iagocharon.api.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    Optional<Proyecto> findById(int id);

    List<Proyecto> findAllByOrderByIdAsc();

    boolean existsById(int id);
}
