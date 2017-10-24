package com.example.jimmypi.jimmypi.Objetos;

/**
 * Created by Jimmy on 13/09/2017.
 */

public class Atajo {

    private String atajo;
    private String accion;

    public Atajo() {

    }

    public Atajo(String atajo, String accion) {
        this.atajo = atajo;
        this.accion = accion;
    }



    public String getAtajo() {
        return atajo;
    }

    public void setAtajo(String atajo) {
        this.atajo = atajo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}