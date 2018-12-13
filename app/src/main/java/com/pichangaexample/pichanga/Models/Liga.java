package com.pichangaexample.pichanga.Models;

import java.util.ArrayList;

public class Liga {
    private String ID;
    private String idCreador;
    private String nombre;
    private ArrayList<Equipo> listaEquipos;
    private ArrayList<Partido> listaPartidos;


    public Liga(){
        listaEquipos = new ArrayList<>();
        listaPartidos = new ArrayList<>();
    }

    //get methods

    public String getID() {
        return ID;
    }

    public String getIdCreador() {
        return idCreador;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public ArrayList<Partido> getListaPartidos() {
        return listaPartidos;
    }


    //set methods

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setIdCreador(String idCreador) {
        this.idCreador = idCreador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setListaEquipos(ArrayList<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    public void setListaPartidos(ArrayList<Partido> listaPartidos) {
        this.listaPartidos = listaPartidos;
    }

}
