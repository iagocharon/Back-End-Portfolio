package com.iagocharon.api.security.controller;

import com.iagocharon.api.dto.Mensaje;
import com.iagocharon.api.dto.UsuarioDto;
import com.iagocharon.api.entity.Usuario;
import com.iagocharon.api.security.dto.JwtDto;
import com.iagocharon.api.security.dto.LoginUsuario;
import com.iagocharon.api.security.dto.NuevoUsuario;
import com.iagocharon.api.security.entity.Rol;
import com.iagocharon.api.security.jwt.JwtProvider;
import com.iagocharon.api.security.service.RolService;
import com.iagocharon.api.service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        }
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("El usuario es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevoUsuario.getEmail())) {
            return new ResponseEntity(new Mensaje("El email es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevoUsuario.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevoUsuario.getApellido())) {
            return new ResponseEntity(new Mensaje("El apellido es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevoUsuario.getTitulo())) {
            return new ResponseEntity(new Mensaje("El titulo es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevoUsuario.getImagenPerfil())) {
            return new ResponseEntity(new Mensaje("La imagen de perfil es obligatoria."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevoUsuario.getImagenPortada())) {
            return new ResponseEntity(new Mensaje("La imagen de portada es obligatoria."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevoUsuario.getInfo())) {
            return new ResponseEntity(new Mensaje("La info es obligatoria."), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario =
                new Usuario(nuevoUsuario.getEmail(), nuevoUsuario.getNombre(), nuevoUsuario.getApellido(), nuevoUsuario.getTitulo(), nuevoUsuario.getImagenPerfil(),
                        nuevoUsuario.getImagenPortada(), nuevoUsuario.getInfo(), nuevoUsuario.getWhatsapp(), nuevoUsuario.getInstagram(), nuevoUsuario.getFacebook(), nuevoUsuario.getTwitter(), nuevoUsuario.getGithub(), nuevoUsuario.getNombreUsuario(), passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre("USER").get());
        if(nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre("ADMIN").get());
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody UsuarioDto usuarioDto) {
        if (!usuarioService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        if ((usuarioService.existsByNombreUsuario(usuarioDto.getNombreUsuario())) && (usuarioService.getByNombreUsuario(usuarioDto.getNombreUsuario()).get().getId() != id)) {
            return new ResponseEntity(new Mensaje("El usuario ya existe."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("El usuario es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if ((usuarioService.existsByEmail(usuarioDto.getEmail())) && (usuarioService.getByEmail(usuarioDto.getEmail()).get().getId() != id)) {
            return new ResponseEntity(new Mensaje("El email ya existe."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getEmail())) {
            return new ResponseEntity(new Mensaje("El email es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getApellido())) {
            return new ResponseEntity(new Mensaje("El apellido es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getTitulo())) {
            return new ResponseEntity(new Mensaje("El titulo es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getImagenPerfil())) {
            return new ResponseEntity(new Mensaje("La imagen de perfil es obligatoria."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getImagenPortada())) {
            return new ResponseEntity(new Mensaje("La imagen de portada es obligatoria."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(usuarioDto.getInfo())) {
            return new ResponseEntity(new Mensaje("La info es obligatoria."), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = usuarioService.getOne(id).get();
        usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setTitulo(usuarioDto.getTitulo());
        usuario.setImagenPerfil(usuarioDto.getImagenPerfil());
        usuario.setImagenPortada(usuarioDto.getImagenPortada());
        usuario.setInfo(usuarioDto.getInfo());

        return new ResponseEntity(new Mensaje("Usuario actualizado"), HttpStatus.OK);
    }


}
