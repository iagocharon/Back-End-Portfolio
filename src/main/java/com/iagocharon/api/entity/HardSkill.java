package com.iagocharon.api.entity;

import javax.persistence.*;

@Entity
public class HardSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int nivel;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public HardSkill() {
    }

    public HardSkill(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void assignUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
