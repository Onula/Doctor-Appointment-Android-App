package com.example.r3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r3.databinding.FragmentDocMenuBinding;
import com.example.r3.databinding.FragmentPSFmemberMenuBinding;


public class PSFmemberMenu extends Fragment {

    private FragmentPSFmemberMenuBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPSFmemberMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PSFmemberMenu.this)
                        .navigate(R.id.action_PSFmemberMenu2_to_r1CreatePhysiotherapy);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PSFmemberMenu.this)
                        .navigate(R.id.action_PSFmemberMenu2_to_r2CreateProvision);
            }
        });
        binding.imageViewSignout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PSFmemberMenu.this).navigate(R.id.action_PSFmemberMenu2_to_selectRole);
            }
        });
    }


}