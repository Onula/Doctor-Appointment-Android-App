package com.example.r3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r3.databinding.FragmentDocMenuBinding;
import com.example.r3.databinding.FragmentR7DisplayAndManagementOfRequestsBinding;


public class R7DisplayAndManagementOfRequests extends Fragment {
    private FragmentR7DisplayAndManagementOfRequestsBinding binding;
    private String userID;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentR7DisplayAndManagementOfRequestsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Taking the Doctor UserID from the bundle of DocMenuFragment.java
        userID = getArguments().getString("UserID");

        binding.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(R7DisplayAndManagementOfRequests.this)
                        .navigate(R.id.action_r7DisplayAndManagementOfRequests_to_selectRole);
            }
        });
        binding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(R7DisplayAndManagementOfRequests.this)
                        .navigate(R.id.action_r7DisplayAndManagementOfRequests_to_DocMenu);
            }

    });

}
}
