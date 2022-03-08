package com.example.mvvmdos.manage;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmdos.model.Alumno;
import com.example.mvvmdos.model.Repository;

public class ManageViewModel extends ViewModel {
    MutableLiveData<Alumno> alumno;
    MutableLiveData<String> buttonText;
    ManageCallback callback;

    public interface ManageCallback{
        void sendNotification();
    }

    public MutableLiveData<String> getButtonText() {
        return buttonText;
    }

    public MutableLiveData<Alumno> getAlumno() {
        return alumno;
    }

    public ManageViewModel(Alumno alumno, ManageCallback callback) {
        if (alumno == null){
            this.alumno = new MutableLiveData<>(new Alumno("",""));
            this.buttonText = new MutableLiveData<>("Add");
            this.callback = callback;
        }else{
            this.alumno = new MutableLiveData<>(alumno);
            this.buttonText = new MutableLiveData<>("Edit");
            this.callback = callback;
        }
    }

    public void manage(){
        if (buttonText.getValue().equals("Add")){
            Repository.getInstance().add(this.alumno.getValue());
        }else{
            Repository.getInstance().edit(this.alumno.getValue());
        }
        this.callback.sendNotification();
    }
}
