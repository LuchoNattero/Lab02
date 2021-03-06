package com.lrnl.lab02.modelo;

import java.util.Date;

/**
 * Created by usuario on 29/09/2017.
 */
public class Tarjeta implements java.io.Serializable{

    private String nombre;
    private Integer numero;
    private Integer seguridad;
    private  String correo;
    private Date fechaVencimiento;

    @Override
    public String toString() {
        return "Tarjeta{" +
                "nombre='" + nombre + '\'' +
                ", numero=" + numero +
                ", seguridad=" + seguridad +
                ", fechaVencimiento=" + fechaVencimiento +
                '}';
    }


    public Tarjeta(){}

    public Tarjeta(String nombre, Integer numero, Integer seguridad,String correo, Date fechaVencimiento) {
        this.nombre = nombre;
        this.numero = numero;
        this.seguridad = seguridad;
        this.correo = correo;
        this.fechaVencimiento = fechaVencimiento;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {this.correo = correo; }

    public  String getCorreo(){return  correo;}

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }


    public Integer getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(Integer seguridad) {
        this.seguridad = seguridad;
    }


    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }


}
