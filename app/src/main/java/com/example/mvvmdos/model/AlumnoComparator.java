package com.example.mvvmdos.model;

import java.util.Comparator;

public class AlumnoComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return ((Alumno)o1).getApellido().compareTo(((Alumno)o2).getApellido());
    }
}
