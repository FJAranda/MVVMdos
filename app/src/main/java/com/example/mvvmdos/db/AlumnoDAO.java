package com.example.mvvmdos.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmdos.model.Alumno;

import java.util.List;

@Dao
public interface AlumnoDAO {
    @Insert
    long insert(Alumno alumno);

    @Update
    void update(Alumno alumno);

    @Delete
    void delete(Alumno alumno);

    @Query("SELECT * FROM alumno ORDER BY nombre ASC")
    LiveData<List<Alumno>> selectAll();
}
