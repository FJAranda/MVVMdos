package com.example.mvvmdos.model;

import androidx.lifecycle.LiveData;

import com.example.mvvmdos.db.AlumnoDAO;
import com.example.mvvmdos.db.MyDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Repository {
    private static Repository repository;
    private AlumnoDAO dao;

    private Repository() {
        dao = MyDatabase.getDatabase().alumnoDAO();
    }

    public static Repository getInstance(){
        if (repository == null){
            repository = new Repository();
        }
        return repository;
    }

    public LiveData<List<Alumno>> getList(){
        try {
            return MyDatabase.databaseWriteExecutor.submit(() -> dao.selectAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(Alumno alumno){
        MyDatabase.databaseWriteExecutor.submit(() -> dao.insert(alumno));
    }

    public void edit(Alumno alumno){
        MyDatabase.databaseWriteExecutor.submit(() -> dao.update(alumno));
    }

    public void delete(Alumno alumno){
        MyDatabase.databaseWriteExecutor.submit(() -> dao.delete(alumno));
    }
}
