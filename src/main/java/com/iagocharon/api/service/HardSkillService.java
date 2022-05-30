package com.iagocharon.api.service;

import com.iagocharon.api.entity.HardSkill;
import com.iagocharon.api.repository.HardSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HardSkillService {

    @Autowired
    HardSkillRepository hardSkillRepository;

    public List<HardSkill> list() {
        return hardSkillRepository.findAllByOrderByIdAsc();
    }

    public Optional<HardSkill> getOne(int id) {
        return hardSkillRepository.findById(id);
    }

    public void save(HardSkill hardSkill) {
        hardSkillRepository.save(hardSkill);
    }

    public void delete(int id) {
        hardSkillRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return hardSkillRepository.existsById(id);
    }

}
