package com.fran.petfeed.capadatos;

import java.io.Serializable;

public class Mascota implements Serializable {

    private Integer id;
    private String nombre;
    private Integer iddueno;
    private Integer edad;
    private String genero;
    private String tipo;
    private String raza;
    private String tamano;
    private String cumpleanos;
    private String celo;

    public Mascota(Integer id, String nombre, Integer iddueno, Integer edad, String genero, String tipo, String raza, String tamano, String cumpleanos, String celo) {
        this.id = id;
        this.nombre = nombre;
        this.iddueno = iddueno;
        this.edad = edad;
        this.genero = genero;
        this.tipo = tipo;
        this.raza = raza;
        this.tamano = tamano;
        this.cumpleanos = cumpleanos;
        this.celo = celo;
    }

    public Mascota(){

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

    public Integer getIddueno() {
        return iddueno;
    }

    public void setIddueno(Integer dueno) {
        this.iddueno = iddueno;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getCumpleanos() {
        return cumpleanos;
    }

    public void setCumpleanos(String cumpleanos) {
        this.cumpleanos = cumpleanos;
    }

    public String getCelo() {
        return celo;
    }

    public void setCelo(String celo) {
        this.celo = celo;
    }
}
