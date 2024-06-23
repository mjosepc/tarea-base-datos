package org.example;

import java.sql.Date;
import java.sql.Date;

public class Reserva {
    private int idReserva;
    private int dniCliente;
    private int idHabitacion;
    private Date fechaCheckin;
    private Date fechaCheckout;

    public Reserva(int idReserva, int dniCliente, int idHabitacion, Date fechaCheckin, Date fechaCheckout) {
        this.idReserva = idReserva;
        this.dniCliente = dniCliente;
        this.idHabitacion = idHabitacion;
        this.fechaCheckin = fechaCheckin;
        this.fechaCheckout = fechaCheckout;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(int dniCliente) {
        this.dniCliente = dniCliente;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Date getFechaCheckin() {
        return fechaCheckin;
    }

    public void setFechaCheckin(Date fechaCheckin) {
        this.fechaCheckin = fechaCheckin;
    }

    public Date getFechaCheckout() {
        return fechaCheckout;
    }

    public void setFechaCheckout(Date fechaCheckout) {
        this.fechaCheckout = fechaCheckout;
    }
}
