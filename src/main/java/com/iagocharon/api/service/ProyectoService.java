package com.iagocharon.api.service;

import com.iagocharon.api.entity.Proyecto;
import com.iagocharon.api.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProyectoService {

    @Autowired
    ProyectoRepository proyectoRepository;

    public List<Proyecto> list() {
        return proyectoRepository.findAllByOrderByIdAsc();
    }

    public Optional<Proyecto> getOne(int id) {
        return proyectoRepository.findById(id);
    }

    public void save(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    public void delete(int id) {
        proyectoRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return proyectoRepository.existsById(id);
    }
}
