package com.iagocharon.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iagocharon.api.security.entity.Rol;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String email;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private String titulo;
    @NotNull
    private String imagenPerfil;
    @NotNull
    private String imagenPortada;
    @NotNull
    private String info;
    private String whatsapp;
    private String instagram;
    private String facebook;
    private String twitter;
    private String github;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Experiencia> experiencias = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Educacion> educaciones = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HardSkill> hardSkills = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SoftSkill> softSkills = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SoftSkill> proyectos = new HashSet<>();
    @NotNull
    @Column(unique = true)
    private String nombreUsuario;
    @NotNull
    private String password;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();


    public Usuario() {
    }

    public Usuario(String email, String nombre, String apellido, String titulo, String imagenPerfil, String imagenPortada, String info, String whatsapp, String instagram, String facebook, String twitter, String github, String nombreUsuario, String password) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo = titulo;
        this.imagenPerfil = imagenPerfil;
        this.imagenPortada = imagenPortada;
        this.info = info;
        this.whatsapp = whatsapp;
        this.instagram = instagram;
        this.facebook = facebook;
        this.twitter = twitter;
        this.github = github;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public String getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(String imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public Set<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(Set<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }

    public Set<Educacion> getEducaciones() {
        return educaciones;
    }

    public void setEducaciones(Set<Educacion> educaciones) {
        this.educaciones = educaciones;
    }

    public Set<HardSkill> getHardSkills() {
        return hardSkills;
    }

    public void setHardSkills(Set<HardSkill> hardSkills) {
        this.hardSkills = hardSkills;
    }

    public Set<SoftSkill> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(Set<SoftSkill> softSkills) {
        this.softSkills = softSkills;
    }

    public Set<SoftSkill> getProyectos() {
        return proyectos;
    }

    public void setProyectos(Set<SoftSkill> proyectos) {
        this.proyectos = proyectos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
