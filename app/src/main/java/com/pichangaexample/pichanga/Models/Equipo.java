package com.pichangaexample.pichanga.Models;

public class Equipo {

    private String nombre;
    private int puntos;

    public Equipo(String nombre){
        this.nombre = nombre;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        String equipo = "{" +
                "nombre: "+this.nombre + "," +
                "puntos: "+Integer.toString(this.puntos) + "," +
                "}";
        return equipo;
    }
}
