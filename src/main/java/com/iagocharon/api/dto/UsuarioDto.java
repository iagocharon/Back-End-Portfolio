package com.iagocharon.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioDto {

    @NotBlank
    private String nombreUsuario;
    @Email
    private String email;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String titulo;
    @NotBlank
    private String imagenPerfil;
    @NotBlank
    private String imagenPortada;
    @NotBlank
    private String info;
    private String whatsapp;
    private String instagram;
    private String facebook;
    private String twitter;
    private String github;

    public UsuarioDto() {
    }

    public UsuarioDto(String nombreUsuario, String email, String nombre, String apellido, String titulo, String imagenPerfil, String imagenPortada, String info, String whatsapp, String instagram, String facebook, String twitter, String github) {
        this.nombreUsuario = nombreUsuario;
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
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
}
