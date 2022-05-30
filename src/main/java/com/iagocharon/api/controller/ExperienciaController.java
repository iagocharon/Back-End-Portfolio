package com.iagocharon.api.controller;

import com.iagocharon.api.dto.ExperienciaDto;
import com.iagocharon.api.dto.Mensaje;
import com.iagocharon.api.entity.Experiencia;
import com.iagocharon.api.entity.Usuario;
import com.iagocharon.api.service.ExperienciaService;
import com.iagocharon.api.service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/experiencia")
@CrossOrigin(origins = "*")
public class ExperienciaController {

    @Autowired
    ExperienciaService experienciaService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = experienciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Experiencia experiencia = experienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    @PostMapping("/create/usuario/{usuarioId}")
    public ResponseEntity<?> create(@RequestBody ExperienciaDto experienciaDto, @PathVariable("usuarioId") int usuarioId) {
        if (StringUtils.isBlank(experienciaDto.getCargo())) {
            return new ResponseEntity(new Mensaje("El cargo es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(experienciaDto.getInstitucion())) {
            return new ResponseEntity(new Mensaje("La institucion es obligatoria."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(experienciaDto.getLogoInstitucion())) {
            return new ResponseEntity(new Mensaje("El logo es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (experienciaDto.getAnioInicio() < 0) {
            return new ResponseEntity(new Mensaje("El año de inicio es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (experienciaDto.getAnioFin() < 0) {
            return new ResponseEntity(new Mensaje("El año de fin es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(experienciaDto.getLugar())) {
            return new ResponseEntity(new Mensaje("El lugar es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        Experiencia experiencia = new Experiencia(experienciaDto.getCargo(), experienciaDto.getInstitucion(), experienciaDto.getLogoInstitucion(), experienciaDto.getAnioInicio(), experienciaDto.getAnioFin(), experienciaDto.getLugar());
        Usuario usuario = usuarioService.getOne(usuarioId).get();
        experiencia.assignUsuario(usuario);
        experienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia creada."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ExperienciaDto experienciaDto){
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(experienciaDto.getCargo())) {
            return new ResponseEntity(new Mensaje("El cargo es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(experienciaDto.getInstitucion())) {
            return new ResponseEntity(new Mensaje("La institucion es obligatoria."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(experienciaDto.getLogoInstitucion())) {
            return new ResponseEntity(new Mensaje("El logo es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (experienciaDto.getAnioInicio() < 0) {
            return new ResponseEntity(new Mensaje("El año de inicio es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (experienciaDto.getAnioFin() < 0) {
            return new ResponseEntity(new Mensaje("El año de fin es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(experienciaDto.getLugar())) {
            return new ResponseEntity(new Mensaje("El lugar es obligatorio."), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = experienciaService.getOne(id).get();
        experiencia.setCargo(experienciaDto.getCargo());
        experiencia.setInstitucion(experienciaDto.getInstitucion());
        experiencia.setLogoInstitucion(experienciaDto.getLogoInstitucion());
        experiencia.setAnioInicio(experienciaDto.getAnioInicio());
        experiencia.setAnioFin(experienciaDto.getAnioFin());
        experiencia.setLugar(experienciaDto.getLugar());
        experienciaService.save(experiencia);

        return new ResponseEntity(new Mensaje("Experiencia actualizada."), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id) {
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe."), HttpStatus.OK);
        }
        experienciaService.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia elminiada."), HttpStatus.OK);
    }
}
