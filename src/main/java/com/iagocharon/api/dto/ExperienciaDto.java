package com.iagocharon.api.dto;

import javax.validation.constraints.NotBlank;

public class ExperienciaDto {

    @NotBlank
    private String cargo;
    @NotBlank
    private String institucion;
    @NotBlank
    private String logoInstitucion;
    @NotBlank
    private int anioInicio;
    @NotBlank
    private int anioFin;
    @NotBlank
    private String lugar;

    public ExperienciaDto() {
    }

    public ExperienciaDto(String cargo, String institucion, String logoInstitucion, int anioInicio, int anioFin, String lugar) {
        this.cargo = cargo;
        this.institucion = institucion;
        this.logoInstitucion = logoInstitucion;
        this.anioInicio = anioInicio;
        this.anioFin = anioFin;
        this.lugar = lugar;
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
}
