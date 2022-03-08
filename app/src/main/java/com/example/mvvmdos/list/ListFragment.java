package com.example.mvvmdos.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmdos.R;
import com.example.mvvmdos.databinding.FragmentListBinding;
import com.example.mvvmdos.model.Alumno;

import java.util.ArrayList;

public class ListFragment extends Fragment implements ListAdapter.OnManageAlumnoListener {
    FragmentListBinding binding;
    private ListAdapter adapter;
    private ListViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.order_nombre:
                viewModel.order();
                adapter.update(viewModel.getList().getValue());
                return true;
            case R.id.order_apellido:
                viewModel.orderApellido();
                adapter.update(viewModel.getList().getValue());
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ListViewModel.class);
        viewModel.getList().observe(getViewLifecycleOwner(), alumnos ->{
            adapter.update(alumnos);
        });
        binding.setViewmodel(viewModel);
        initRV();
        initFab();
    }

    private void initFab() {
        binding.fabList.setOnClickListener( v->{
            ListFragmentDirections.ActionListFragmentToManageFragment action = ListFragmentDirections.actionListFragmentToManageFragment(null);
            NavHostFragment.findNavController(this).navigate(action);
        });
    }

    private void initRV() {
        adapter = new ListAdapter(new ArrayList<>(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.rvList.setLayoutManager(linearLayoutManager);
        binding.rvList.setAdapter(adapter);
    }

    @Override
    public void onDeleteAlumno(Alumno alumno) {
        viewModel.delete(alumno);
    }

    @Override
    public void onEditAlumno(Alumno alumno) {
        ListFragmentDirections.ActionListFragmentToManageFragment action = ListFragmentDirections.actionListFragmentToManageFragment(alumno);
        NavHostFragment.findNavController(this).navigate(action);
    }
}