package com.iagocharon.api.dto;

import javax.validation.constraints.NotBlank;

public class SoftSkillDto {

    @NotBlank
    private String nombre;

    public SoftSkillDto() {
    }

    public SoftSkillDto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
