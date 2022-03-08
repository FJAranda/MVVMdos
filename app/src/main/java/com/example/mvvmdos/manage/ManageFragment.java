package com.example.mvvmdos.manage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDeepLinkBuilder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmdos.MVVMDosApplication;
import com.example.mvvmdos.R;
import com.example.mvvmdos.databinding.FragmentManageBinding;
import com.example.mvvmdos.model.Alumno;

import java.util.Random;

public class ManageFragment extends Fragment implements ManageViewModel.ManageCallback {
    FragmentManageBinding binding;
    ManageViewModel viewModel;

    @Override
    public void sendNotification() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Alumno.TAG, binding.getViewmodel().alumno.getValue());
        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.manageFragment)
                .setArguments(bundle)
                .createPendingIntent();
        Notification.Builder builder = new Notification.Builder(getActivity(), MVVMDosApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Titulo notificacion")
                .setContentText("Texto notificacion")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(1000), builder.build());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManageBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (ManageFragmentArgs.fromBundle(getArguments()).getAlumno() != null){
            viewModel = new ViewModelProvider(this, new ManageViewModelFactory(ManageFragmentArgs.fromBundle(getArguments()).getAlumno(), this)).get(ManageViewModel.class);
        }else{
            viewModel = new ViewModelProvider(this, new ManageViewModelFactory(null, this)).get(ManageViewModel.class);
        }
        binding.setLifecycleOwner(this);
        binding.setViewmodel(viewModel);
        binding.executePendingBindings();
        viewModel.getAlumno().observe(getViewLifecycleOwner(), object ->{
        });
        viewModel.getButtonText().observe(getViewLifecycleOwner(), object ->{
        });
    }
}