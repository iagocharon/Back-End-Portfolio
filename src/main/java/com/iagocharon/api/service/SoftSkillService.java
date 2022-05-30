package com.iagocharon.api.service;

import com.iagocharon.api.entity.SoftSkill;
import com.iagocharon.api.repository.SoftSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SoftSkillService {

    @Autowired
    SoftSkillRepository softSkillRepository;

    public List<SoftSkill> list() {
        return softSkillRepository.findAllByOrderByIdAsc();
    }

    public Optional<SoftSkill> getOne(int id) {
        return softSkillRepository.findById(id);
    }

    public void save(SoftSkill softSkill) {
        softSkillRepository.save(softSkill);
    }

    public void delete(int id) {
        softSkillRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return softSkillRepository.existsById(id);
    }

}
