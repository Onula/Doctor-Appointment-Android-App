package com.example.r3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r3.databinding.FragmentR9AppointmenetRequestBinding;
import com.example.r3.databinding.FragmentR9AppointmentRequestBBinding;

public class R9AppointmentRequestB extends Fragment {

    FragmentR9AppointmentRequestBBinding binding;

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = FragmentR9AppointmentRequestBBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        binding.previousPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R9AppointmentRequestB.this)
                        .navigate(R.id.action_r9AppointmentRequestB_to_r9AppointmenetRequest);
            }
        });

    }
}