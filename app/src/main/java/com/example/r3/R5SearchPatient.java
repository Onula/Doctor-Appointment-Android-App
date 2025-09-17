package com.example.r3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r3.databinding.FragmentR3CreatePatientBinding;
import com.example.r3.databinding.FragmentR5SearchPatientBinding;


public class R5SearchPatient extends Fragment {

 private FragmentR5SearchPatientBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = FragmentR5SearchPatientBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        binding.primary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R5SearchPatient.this).navigate(R.id.action_r5SearchPatient_to_DocMenu);

            }
        });
        binding.primaryEk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R5SearchPatient.this).navigate(R.id.action_r5SearchPatient_to_selectRole);

            }
        });
        binding.primaryEk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R5SearchPatient.this).navigate(R.id.action_r5SearchPatient_to_DocMenu);

            }
        });



    }


}