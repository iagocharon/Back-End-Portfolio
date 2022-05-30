package com.iagocharon.api.controller;

import com.iagocharon.api.dto.Mensaje;
import com.iagocharon.api.dto.SoftSkillDto;
import com.iagocharon.api.entity.Usuario;
import com.iagocharon.api.entity.SoftSkill;
import com.iagocharon.api.service.UsuarioService;
import com.iagocharon.api.service.SoftSkillService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/softSkill")
@CrossOrigin(origins = "*")
public class SoftSkillController {

    @Autowired
    SoftSkillService softSkillService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<SoftSkill>> list() {
        List<SoftSkill> list = softSkillService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<SoftSkill> getById(@PathVariable("id") int id) {
        if (!softSkillService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        SoftSkill softSkill = softSkillService.getOne(id).get();
        return new ResponseEntity(softSkill, HttpStatus.OK);
    }

    @PostMapping("/create/usuario/{usuarioId}")
    public ResponseEntity<?> create(@RequestBody SoftSkillDto softSkillDto, @PathVariable("usuarioId") int usuarioId) {
        if (StringUtils.isBlank(softSkillDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        SoftSkill softSkill = new SoftSkill(softSkillDto.getNombre());
        Usuario usuario = usuarioService.getOne(usuarioId).get();
        softSkill.assignUsuario(usuario);
        softSkillService.save(softSkill);
        return new ResponseEntity(new Mensaje("SoftSkill creada."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody SoftSkillDto softSkillDto) {
        if (!softSkillService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(softSkillDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        SoftSkill softSkill = softSkillService.getOne(id).get();
        softSkill.setNombre(softSkillDto.getNombre());
        softSkillService.save(softSkill);
        return new ResponseEntity(new Mensaje("SoftSkill actualizada."), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!softSkillService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe."), HttpStatus.OK);
        }
        softSkillService.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia elminiada."), HttpStatus.OK);
    }

}
