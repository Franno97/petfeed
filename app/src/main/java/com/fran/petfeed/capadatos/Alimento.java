package com.fran.petfeed.capadatos;

public class Alimento {
    private Integer id;
    private String nombre;
    private String descripcion;
    private String tipoAnimal;
    private String toxicidad;
    private String compuesto;

    public Alimento() {
    }

    public Alimento(Integer id, String nombre, String descripcion, String tipoAnimal, String toxicidad, String compuesto) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoAnimal = tipoAnimal;
        this.toxicidad = toxicidad;
        this.compuesto = compuesto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getToxicidad() {
        return toxicidad;
    }

    public void setToxicidad(String toxicidad) {
        this.toxicidad = toxicidad;
    }

    public String getCompuesto() {
        return compuesto;
    }

    public void setCompuesto(String compuesto) {
        this.compuesto = compuesto;
    }
}
