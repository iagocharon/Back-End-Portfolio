package com.iagocharon.api.repository;

import com.iagocharon.api.entity.SoftSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SoftSkillRepository extends JpaRepository<SoftSkill, Integer> {

    Optional<SoftSkill> findById(int id);

    List<SoftSkill> findAllByOrderByIdAsc();

    boolean existsById(int id);
}
