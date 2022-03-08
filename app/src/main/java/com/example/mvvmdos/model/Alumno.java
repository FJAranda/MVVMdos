package com.example.mvvmdos.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Alumno implements Comparable, Serializable {
    public static final String TAG = "alumno";
    @PrimaryKey(autoGenerate = true)
    int id;
    String nombre;
    String apellido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Alumno(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Ignore
    public Alumno(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Ignore
    public Alumno() {
    }

    @Override
    public int compareTo(Object o) {
        if (((Alumno)o).getNombre().equals(getNombre())){
            return((Alumno)o).getApellido().compareTo(getApellido());
        }else{
            return ((Alumno)o).getNombre().compareTo(getNombre());
        }
    }
}
