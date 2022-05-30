package com.iagocharon.api.controller;

import com.iagocharon.api.dto.Mensaje;
import com.iagocharon.api.dto.UsuarioDto;
import com.iagocharon.api.entity.*;
import com.iagocharon.api.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ExperienciaService experienciaService;
    @Autowired
    EducacionService educacionService;
    @Autowired
    HardSkillService hardSkillService;
    @Autowired
    SoftSkillService softSkillService;

    @Autowired
    ProyectoService proyectoService;

    @GetMapping("/detail/{username}")
    public ResponseEntity<Usuario> getByUsername (@PathVariable("username") String username) {
        if (!usuarioService.existsByNombreUsuario(username)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuarioService.getByNombreUsuario(username).get();
        return new ResponseEntity(usuario, HttpStatus.OK);
    }

    @GetMapping("/experiencias/{username}")
    public ResponseEntity<?> getExperiencias (@PathVariable("username") String username) {
        List<Experiencia> experiencias = experienciaService.list();
        List<Experiencia> experienciasUsuario = new ArrayList<Experiencia>();
        for (Experiencia experiencia: experiencias) {
            if (experiencia.getUsuario().getNombreUsuario().equals(username)) {
                experienciasUsuario.add(experiencia);
            }
        }
        return new ResponseEntity(experienciasUsuario, HttpStatus.OK);
    }

    @GetMapping("/educaciones/{username}")
    public ResponseEntity<?> getEducaciones (@PathVariable("username") String username) {
        List<Educacion> educaciones = educacionService.list();
        List<Educacion> educacionesUsuario = new ArrayList<Educacion>();
        for (Educacion educacion: educaciones) {
            if (Objects.equals(educacion.getUsuario().getNombreUsuario(), username)) {
                educacionesUsuario.add(educacion);
            }
        }
        return new ResponseEntity(educacionesUsuario, HttpStatus.OK);
    }

    @GetMapping("/hardskills/{username}")
    public ResponseEntity<?> getHardSkills (@PathVariable("username") String username) {
        List<HardSkill> hardSkills = hardSkillService.list();
        List<HardSkill> hardSkillsUsuario = new ArrayList<HardSkill>();
        for (HardSkill hardSkill: hardSkills) {
            if (Objects.equals(hardSkill.getUsuario().getNombreUsuario(), username)) {
                hardSkillsUsuario.add(hardSkill);
            }
        }
        return new ResponseEntity(hardSkillsUsuario, HttpStatus.OK);
    }

    @GetMapping("/softskills/{username}")
    public ResponseEntity<?> getSoftSkills (@PathVariable("username") String username) {
        List<SoftSkill> softSkills = softSkillService.list();
        List<SoftSkill> softSkillsUsuario = new ArrayList<SoftSkill>();
        for (SoftSkill softSkill: softSkills) {
            if (Objects.equals(softSkill.getUsuario().getNombreUsuario(), username)) {
                softSkillsUsuario.add(softSkill);
            }
        }
        return new ResponseEntity(softSkillsUsuario, HttpStatus.OK);
    }

    @GetMapping("/proyectos/{username}")
    public ResponseEntity<?> getProyectos (@PathVariable("username") String username) {
        List<Proyecto> proyectos = proyectoService.list();
        List<Proyecto> proyectosUsuario = new ArrayList<Proyecto>();
        for (Proyecto proyecto: proyectos) {
            if (Objects.equals(proyecto.getUsuario().getNombreUsuario(), username)) {
                proyectosUsuario.add(proyecto);
            }
        }
        return new ResponseEntity(proyectosUsuario, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UsuarioDto usuarioDto) {
        if (!usuarioService.existsByNombreUsuario(usuarioDto.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(usuarioDto.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("El usuario es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getEmail())) {
            return new ResponseEntity(new Mensaje("El email es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getApellido())) {
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getTitulo())) {
            return new ResponseEntity(new Mensaje("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getImagenPerfil())) {
            return new ResponseEntity(new Mensaje("La imagen de perfil es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getImagenPortada())) {
            return new ResponseEntity(new Mensaje("La imagen de portada es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getInfo())) {
            return new ResponseEntity(new Mensaje("La info es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = usuarioService.getByNombreUsuario(usuarioDto.getNombreUsuario()).get();
        usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setTitulo(usuarioDto.getTitulo());
        usuario.setImagenPerfil(usuarioDto.getImagenPerfil());
        usuario.setImagenPortada(usuarioDto.getImagenPortada());
        usuario.setInfo(usuarioDto.getInfo());
        usuario.setWhatsapp(usuarioDto.getWhatsapp());
        usuario.setInstagram(usuarioDto.getInstagram());
        usuario.setFacebook(usuarioDto.getFacebook());
        usuario.setTwitter(usuarioDto.getTwitter());
        usuario.setGithub(usuarioDto.getGithub());
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Usuario actualizado"), HttpStatus.OK);
    }
}
