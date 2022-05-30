package com.iagocharon.api.security.entity;

import com.iagocharon.api.entity.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioPrincipal implements UserDetails {
    private String email;
    private String nombre;
    private String apellido;
    private String titulo;
    private String imagenPerfil;
    private String imagenPortada;
    private String info;
    private String nombreUsuario;
    private String password;
    private  Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(String email, String nombre, String apellido, String titulo, String imagenPerfil, String imagenPortada, String info, String nombreUsuario, String password, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo = titulo;
        this.imagenPerfil = imagenPerfil;
        this.imagenPortada = imagenPortada;
        this.info = info;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.authorities = authorities;
    }

    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                .getRolNombre())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getEmail(), usuario.getNombre(), usuario.getApellido(), usuario.getTitulo(), usuario.getImagenPerfil(), usuario.getImagenPortada(), usuario.getInfo(), usuario.getNombreUsuario(), usuario.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public String getImagenPortada() {
        return imagenPortada;
    }

    public String getInfo() {
        return info;
    }
}
