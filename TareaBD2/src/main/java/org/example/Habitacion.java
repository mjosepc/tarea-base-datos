package org.example;
public class Habitacion {
    private int idHabitacion;
    private String nombreHabitacion;
    private int idTipo;
    private int precioNoche;

    public Habitacion(int idHabitacion, String nombreHabitacion, int idTipo, int precioNoche) {
        this.idHabitacion = idHabitacion;
        this.nombreHabitacion = nombreHabitacion;
        this.idTipo = idTipo;
        this.precioNoche = precioNoche;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getNombreHabitacion() {
        return nombreHabitacion;
    }

    public void setNombreHabitacion(String nombreHabitacion) {
        this.nombreHabitacion = nombreHabitacion;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(int precioNoche) {
        this.precioNoche = precioNoche;
    }
}
