package com.example.mvvmdos.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmdos.R;
import com.example.mvvmdos.databinding.AlumnoItemBinding;
import com.example.mvvmdos.model.Alumno;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArrayList<Alumno> list;
    private OnManageAlumnoListener listener;
    private AlumnoItemBinding mBinding;

    interface OnManageAlumnoListener{
        void onEditAlumno(Alumno alumno);
        void onDeleteAlumno(Alumno alumno);
    }

    public void update(List<Alumno> alumnos){
        this.list.clear();
        this.list.addAll(alumnos);
        notifyDataSetChanged();
    }

    public ListAdapter(ArrayList<Alumno> list, OnManageAlumnoListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlumnoItemBinding alumnoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.alumno_item, parent, false);
        return new ViewHolder(alumnoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull AlumnoItemBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
        }

        public void bind(Alumno alumno, OnManageAlumnoListener listener) {
            mBinding.setAlumno(alumno);
            itemView.setOnClickListener(v -> listener.onEditAlumno(alumno));
            itemView.setOnLongClickListener(v->{
                listener.onDeleteAlumno(alumno);
                return true;
            });
        }
    }
}
