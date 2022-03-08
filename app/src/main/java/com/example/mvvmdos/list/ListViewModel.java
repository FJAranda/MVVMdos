package com.example.mvvmdos.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmdos.model.Alumno;
import com.example.mvvmdos.model.AlumnoComparator;
import com.example.mvvmdos.model.Repository;

import java.util.Collections;
import java.util.List;

public class ListViewModel extends ViewModel {
    private LiveData<List<Alumno>> list;
    private boolean order = false;

    public LiveData<List<Alumno>> getList() {
        return list;
    }

    public ListViewModel() {
        this.list = Repository.getInstance().getList();
    }

    public void delete(Alumno alumno){
        Repository.getInstance().delete(alumno);
    }

    public void order() {
        if (order) {
            order = false;
            Collections.sort(list.getValue());
        }else{
            order = true;
            Collections.reverse(list.getValue());
        }
    }

    public void orderApellido() {
        Collections.sort(list.getValue(), new AlumnoComparator());
    }
}
