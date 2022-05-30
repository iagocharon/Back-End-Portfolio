package com.iagocharon.api.entity;

import javax.persistence.*;

@Entity
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipo;
    private String institucion;
    private String logoInstitucion;
    private int anioInicio;
    private int anioFin;
    private String lugar;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Educacion() {
    }

    public Educacion(String tipo, String institucion, String logoInstitucion, int anioInicio, int anioFin, String lugar) {
        this.tipo = tipo;
        this.institucion = institucion;
        this.logoInstitucion = logoInstitucion;
        this.anioInicio = anioInicio;
        this.anioFin = anioFin;
        this.lugar = lugar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getLogoInstitucion() {
        return logoInstitucion;
    }

    public void setLogoInstitucion(String logoInstitucion) {
        this.logoInstitucion = logoInstitucion;
    }

    public int getAnioInicio() {
        return anioInicio;
    }

    public void setAnioInicio(int anioInicio) {
        this.anioInicio = anioInicio;
    }

    public int getAnioFin() {
        return anioFin;
    }

    public void setAnioFin(int anioFin) {
        this.anioFin = anioFin;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void assignUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
