package com.example.r3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r3.databinding.FragmentR9AppointmenetRequestBinding;


public class R9AppointmenetRequest extends Fragment {
    FragmentR9AppointmenetRequestBinding binding;

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = FragmentR9AppointmenetRequestBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        binding.homePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R9AppointmenetRequest.this)
                        .navigate(R.id.action_r9AppointmenetRequest_to_patientMenuFragment);
            }
        });

        binding.menuPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R9AppointmenetRequest.this)
                        .navigate(R.id.action_r9AppointmenetRequest_to_patientMenuFragment);

            }
        });

        binding.imageViewSignoutout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R9AppointmenetRequest.this)
                        .navigate(R.id.action_r9AppointmenetRequest_to_selectRole);

            }
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R9AppointmenetRequest.this)
                        .navigate(R.id.action_r9AppointmenetRequest_to_r9AppointmentRequestB);

            }
        });
    }

}