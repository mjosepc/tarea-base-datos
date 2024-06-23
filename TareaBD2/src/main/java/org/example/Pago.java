package org.example;

import java.sql.Date;

public class Pago {
    private int idPago;
    private int idReserva;
    private String tipoPago;
    private int monto;
    private Date fechaPago;

    // Constructor
    public Pago(int idPago, int idReserva, String tipoPago, int monto, Date fechaPago) {
        this.idPago = idPago;
        this.idReserva = idReserva;
        this.tipoPago = tipoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

    // Getters
    public int getIdPago() {
        return idPago;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public int getMonto() {
        return monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    // Setters
    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago=" + idPago +
                ", idReserva=" + idReserva +
                ", tipoPago='" + tipoPago + '\'' +
                ", monto=" + monto +
                ", fechaPago=" + fechaPago +
                '}';
    }
}
