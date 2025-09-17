package com.example.r3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r3.databinding.FragmentPSFmemberMenuBinding;
import com.example.r3.databinding.FragmentPatientMenuBinding;


public class PatientMenuFragment extends Fragment {

    private FragmentPatientMenuBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPatientMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PatientMenuFragment.this)
                        .navigate(R.id.action_patientMenuFragment_to_r9AppointmenetRequest);
            }
        });

        /*binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PatientMenuFragment.this)
                        .navigate(R.id.action_patientMenuFragment_to_r10AnalyticalEconomicState);
            }
        });*/
        binding.imageViewSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PatientMenuFragment.this).navigate(R.id.action_patientMenuFragment_to_selectRole);
            }
        });
    }
}