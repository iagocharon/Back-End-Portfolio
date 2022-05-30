package com.iagocharon.api.dto;

import javax.validation.constraints.NotBlank;

public class ProyectoDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private int mes;
    @NotBlank
    private int anio;
    @NotBlank
    private String link;
    @NotBlank
    private String urlImagen;
    private String descripcion;

    public ProyectoDto() {
    }

    public ProyectoDto(String nombre, int mes, int anio, String link, String urlImagen, String descripcion) {
        this.nombre = nombre;
        this.mes = mes;
        this.anio = anio;
        this.link = link;
        this.urlImagen = urlImagen;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
