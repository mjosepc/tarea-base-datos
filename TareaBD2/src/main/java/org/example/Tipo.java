package org.example;
public class Tipo {
    private int idTipo;
    private String nombre;
    private int capacidad;

    public Tipo(int idTipo, String nombre, int capacidad) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
