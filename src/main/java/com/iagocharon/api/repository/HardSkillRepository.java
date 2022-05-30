package com.iagocharon.api.repository;

import com.iagocharon.api.entity.HardSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HardSkillRepository extends JpaRepository<HardSkill, Integer> {

    Optional<HardSkill> findById(int id);

    List<HardSkill> findAllByOrderByIdAsc();

    boolean existsById(int id);
}
