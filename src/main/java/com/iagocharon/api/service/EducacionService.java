package com.iagocharon.api.service;

import com.iagocharon.api.entity.Educacion;
import com.iagocharon.api.repository.EducacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EducacionService {

    @Autowired
    EducacionRepository educacionRepository;

    public List<Educacion> list() {
        return educacionRepository.findAllByOrderByIdAsc();
    }

    public Optional<Educacion> getOne(int id) {
        return educacionRepository.findById(id);
    }

    public void save(Educacion educacion) {
        educacionRepository.save(educacion);
    }

    public void delete(int id) {
        educacionRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return educacionRepository.existsById(id);
    }
}
