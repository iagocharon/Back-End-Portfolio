package com.iagocharon.api.service;

import com.iagocharon.api.entity.Usuario;
import com.iagocharon.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public Optional<Usuario> getByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Optional<Usuario> getOne(int id) {
        return usuarioRepository.findById(id);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public boolean existsById(int id) {
        return  usuarioRepository.existsById(id);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
