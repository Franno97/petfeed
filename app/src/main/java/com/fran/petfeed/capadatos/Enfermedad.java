package com.fran.petfeed.capadatos;

public class Enfermedad {
    private Integer id;
    private String nombre;
    private String evitar;
    private String afecta_a;
    private String idmascota;

    public Enfermedad(Integer id, String nombre, String evitar, String afecta_a, String idmascota) {
        this.id = id;
        this.nombre = nombre;
        this.evitar = evitar;
        this.afecta_a = afecta_a;
        this.idmascota = idmascota;
    }

    public Enfermedad(){
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

    public String getEvitar() {
        return evitar;
    }

    public void setEvitar(String evitar) {
        this.evitar = evitar;
    }

    public String getAfecta_a() {
        return afecta_a;
    }

    public void setAfecta_a(String afecta_a) {
        this.afecta_a = afecta_a;
    }

    public String getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(String idmascota) {
        this.idmascota = idmascota;
    }
}
