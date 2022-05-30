package com.iagocharon.api.entity;


import javax.persistence.*;

@Entity
public class Experiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cargo;
    private String institucion;
    private String logoInstitucion;
    private int anioInicio;
    private int anioFin;
    private String lugar;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Experiencia() {
    }

    public Experiencia(String cargo, String institucion, String logoInstitucion, int anioInicio, int anioFin, String lugar) {
        this.cargo = cargo;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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
        return this.usuario;
    }

    public void assignUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}