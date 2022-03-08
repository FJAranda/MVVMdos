package com.example.mvvmdos.manage;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmdos.model.Alumno;

public class ManageViewModelFactory implements ViewModelProvider.Factory {
    private Alumno alumno;
    private ManageViewModel.ManageCallback callback;

    public ManageViewModelFactory(Alumno alumno, ManageViewModel.ManageCallback callback){
        this.callback = callback;
        this.alumno = alumno;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ManageViewModel(alumno, callback);
    }
}
