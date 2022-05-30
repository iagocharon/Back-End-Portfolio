package com.iagocharon.api.controller;

import com.iagocharon.api.dto.EducacionDto;
import com.iagocharon.api.dto.Mensaje;
import com.iagocharon.api.entity.Educacion;
import com.iagocharon.api.entity.Usuario;
import com.iagocharon.api.service.EducacionService;
import com.iagocharon.api.service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "*")
public class EducacionController {

    @Autowired
    EducacionService educacionService;
    
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = educacionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Educacion educacion = educacionService.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @PostMapping("/create/usuario/{usuarioId}")
    public ResponseEntity<?> create(@RequestBody EducacionDto educacionDto, @PathVariable("usuarioId") int usuarioId) {
        if (StringUtils.isBlank(educacionDto.getTipo())) {
            return new ResponseEntity(new Mensaje("El tipo es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(educacionDto.getInstitucion())) {
            return new ResponseEntity(new Mensaje("La institucion es obligatoria."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(educacionDto.getLogoInstitucion())) {
            return new ResponseEntity(new Mensaje("El logo es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (educacionDto.getAnioInicio() < 0) {
            return new ResponseEntity(new Mensaje("El año de inicio es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (educacionDto.getAnioFin() < 0) {
            return new ResponseEntity(new Mensaje("El año de fin es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(educacionDto.getLugar())) {
            return new ResponseEntity(new Mensaje("El lugar es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        Educacion educacion = new Educacion(educacionDto.getTipo(), educacionDto.getInstitucion(), educacionDto.getLogoInstitucion(), educacionDto.getAnioInicio(), educacionDto.getAnioFin(), educacionDto.getLugar());
        Usuario usuario = usuarioService.getOne(usuarioId).get();
        educacion.assignUsuario(usuario);
        educacionService.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion creada."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody EducacionDto educacionDto){
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(educacionDto.getTipo())) {
            return new ResponseEntity(new Mensaje("El tipo es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(educacionDto.getInstitucion())) {
            return new ResponseEntity(new Mensaje("La institucion es obligatoria."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(educacionDto.getLogoInstitucion())) {
            return new ResponseEntity(new Mensaje("El logo es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (educacionDto.getAnioInicio() < 0) {
            return new ResponseEntity(new Mensaje("El año de inicio es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (educacionDto.getAnioFin() < 0) {
            return new ResponseEntity(new Mensaje("El año de fin es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(educacionDto.getLugar())) {
            return new ResponseEntity(new Mensaje("El lugar es obligatorio."), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = educacionService.getOne(id).get();
        educacion.setTipo(educacionDto.getTipo());
        educacion.setInstitucion(educacionDto.getInstitucion());
        educacion.setLogoInstitucion(educacionDto.getLogoInstitucion());
        educacion.setAnioInicio(educacionDto.getAnioInicio());
        educacion.setAnioFin(educacionDto.getAnioFin());
        educacion.setLugar(educacionDto.getLugar());
        educacionService.save(educacion);

        return new ResponseEntity(new Mensaje("Educacion actualizada."), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id) {
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe."), HttpStatus.OK);
        }
        educacionService.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia elminiada."), HttpStatus.OK);
    }
}

