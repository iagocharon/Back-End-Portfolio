package com.iagocharon.api.controller;

import com.iagocharon.api.dto.ProyectoDto;
import com.iagocharon.api.dto.Mensaje;
import com.iagocharon.api.entity.Proyecto;
import com.iagocharon.api.entity.Usuario;
import com.iagocharon.api.service.ProyectoService;
import com.iagocharon.api.service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyecto")
@CrossOrigin(origins = "*")
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = proyectoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        if(!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Proyecto proyecto = proyectoService.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }

    @PostMapping("/create/usuario/{usuarioId}")
    public ResponseEntity<?> create(@RequestBody ProyectoDto proyectoDto, @PathVariable("usuarioId") int usuarioId) {
        if (StringUtils.isBlank(proyectoDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (proyectoDto.getMes() < 1 && proyectoDto.getMes() > 12) {
            return new ResponseEntity(new Mensaje("El mes debe ser valido."), HttpStatus.BAD_REQUEST);
        }
        if (proyectoDto.getAnio() < 0) {
            return new ResponseEntity(new Mensaje("El año debe ser valido."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(proyectoDto.getLink())) {
            return new ResponseEntity(new Mensaje("El link es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(proyectoDto.getUrlImagen())) {
            return new ResponseEntity(new Mensaje("La imagen es obligatoria."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(proyectoDto.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria."), HttpStatus.BAD_REQUEST);
        }
        Proyecto proyecto = new Proyecto(proyectoDto.getNombre(), proyectoDto.getMes(), proyectoDto.getAnio(), proyectoDto.getLink(), proyectoDto.getUrlImagen(), proyectoDto.getDescripcion());
        Usuario usuario = usuarioService.getOne(usuarioId).get();
        proyecto.assignUsuario(usuario);
        proyectoService.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto creado."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProyectoDto proyectoDto){
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(proyectoDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (proyectoDto.getMes() < 1 && proyectoDto.getMes() > 12) {
            return new ResponseEntity(new Mensaje("El mes debe ser valido."), HttpStatus.BAD_REQUEST);
        }
        if (proyectoDto.getAnio() < 0) {
            return new ResponseEntity(new Mensaje("El año debe ser valido."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(proyectoDto.getLink())) {
            return new ResponseEntity(new Mensaje("El link es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(proyectoDto.getUrlImagen())) {
            return new ResponseEntity(new Mensaje("La imagen es obligatoria."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(proyectoDto.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria."), HttpStatus.BAD_REQUEST);
        }
        Proyecto proyecto = proyectoService.getOne(id).get();
        proyecto.setNombre(proyectoDto.getNombre());
        proyecto.setMes(proyectoDto.getMes());
        proyecto.setAnio(proyectoDto.getAnio());
        proyecto.setLink(proyectoDto.getLink());
        proyecto.setUrlImagen(proyectoDto.getUrlImagen());
        proyecto.setDescripcion(proyectoDto.getDescripcion());
        proyectoService.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto actualizado."), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe."), HttpStatus.OK);
        }
        proyectoService.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado."), HttpStatus.OK);
    }
}
