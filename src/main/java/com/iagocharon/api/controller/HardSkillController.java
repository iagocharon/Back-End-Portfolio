package com.iagocharon.api.controller;

import com.iagocharon.api.dto.HardSkillDto;
import com.iagocharon.api.dto.Mensaje;
import com.iagocharon.api.entity.HardSkill;
import com.iagocharon.api.entity.Usuario;
import com.iagocharon.api.service.HardSkillService;
import com.iagocharon.api.service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hardSkill")
@CrossOrigin(origins = "*")
public class HardSkillController {

    @Autowired
    HardSkillService hardSkillService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<HardSkill>> list(){
        List<HardSkill> list = hardSkillService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<HardSkill> getById(@PathVariable("id") int id){
        if(!hardSkillService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        HardSkill hardSkill = hardSkillService.getOne(id).get();
        return new ResponseEntity(hardSkill, HttpStatus.OK);
    }

    @PostMapping("/create/usuario/{usuarioId}")
    public ResponseEntity<?> create(@RequestBody HardSkillDto hardSkillDto, @PathVariable("usuarioId") int usuarioId) {
        if (StringUtils.isBlank(hardSkillDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        HardSkill hardSkill = new HardSkill(hardSkillDto.getNombre(), hardSkillDto.getNivel());
        Usuario usuario = usuarioService.getOne(usuarioId).get();
        hardSkill.assignUsuario(usuario);
        hardSkillService.save(hardSkill);
        return new ResponseEntity(new Mensaje("HardSkill creada."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody HardSkillDto hardSkillDto){
        if (!hardSkillService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(hardSkillDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        HardSkill hardSkill = hardSkillService.getOne(id).get();
        hardSkill.setNombre(hardSkillDto.getNombre());
        hardSkill.setNivel(hardSkillDto.getNivel());
        hardSkillService.save(hardSkill);
        return new ResponseEntity(new Mensaje("HardSkill actualizada."), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id) {
        if (!hardSkillService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe."), HttpStatus.OK);
        }
        hardSkillService.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia elminiada."), HttpStatus.OK);
    }
}
