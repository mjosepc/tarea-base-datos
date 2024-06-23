package org.example;

import java.sql.Date;

public class ContratoServicio {
    private int idContrato;
    private int idServicio;
    private int montoTotal;
    private Date fechaContrato;
    private int idReserva; // Nuevo campo agregado

    public ContratoServicio(int idServicio, int montoTotal, Date fechaContrato, int idReserva) {
        this.idServicio = idServicio;
        this.montoTotal = montoTotal;
        this.fechaContrato = fechaContrato;
        this.idReserva = idReserva;
    }

    public ContratoServicio(int idContrato, int idServicio, int montoTotal, Date fechaContrato, int idReserva) {
        this.idContrato = idContrato;
        this.idServicio = idServicio;
        this.montoTotal = montoTotal;
        this.fechaContrato = fechaContrato;
        this.idReserva = idReserva;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
}
