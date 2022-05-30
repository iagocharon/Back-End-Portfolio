package com.iagocharon.api.service;

import com.iagocharon.api.entity.Experiencia;
import com.iagocharon.api.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExperienciaService {

    @Autowired
    ExperienciaRepository experienciaRepository;

    public List<Experiencia> list() {
        return experienciaRepository.findAllByOrderByIdAsc();
    }

    public Optional<Experiencia> getOne(int id) {
        return experienciaRepository.findById(id);
    }

    public void save(Experiencia experiencia) {
        experienciaRepository.save(experiencia);
    }

    public void delete(int id) {
        experienciaRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return experienciaRepository.existsById(id);
    }

}
