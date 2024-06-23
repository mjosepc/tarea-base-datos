package org.example;

public class Servicio {
    private int idServicio;
    private String tipoServicio;
    private String nombreServicio;
    private int precioServicio;

    public Servicio(int idServicio, String tipoServicio, String nombreServicio, int precioServicio) {
        this.idServicio = idServicio;
        this.tipoServicio = tipoServicio;
        this.nombreServicio = nombreServicio;
        this.precioServicio = precioServicio;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public int getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(int precioServicio) {
        this.precioServicio = precioServicio;
    }
}

