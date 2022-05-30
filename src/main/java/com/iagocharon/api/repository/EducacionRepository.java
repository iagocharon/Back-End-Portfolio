package com.iagocharon.api.repository;

import com.iagocharon.api.entity.Educacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducacionRepository extends JpaRepository<Educacion, Integer> {

    Optional<Educacion> findById(int id);

    List<Educacion> findAllByOrderByIdAsc();

    boolean existsById(int id);
}
