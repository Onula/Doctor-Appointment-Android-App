package com.example.r3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.r3.databinding.FragmentDocMenuBinding;

public class DocMenuFragment extends Fragment {
    private FragmentDocMenuBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDocMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Taking the User data from SignIn
        Bundle bundle = new Bundle();
        bundle.putString("UserID", getArguments().getString("UserID"));

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigate to R3CreatePatient & send the User Data
                NavHostFragment.findNavController(DocMenuFragment.this)
                       .navigate(R.id.action_DocMenu_to_R3CreatePatient,bundle);
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DocMenuFragment.this)
                        .navigate(R.id.action_DocMenu_to_r4PatientHistory);
            }
        });
       binding.searchpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DocMenuFragment.this)
                        .navigate(R.id.action_DocMenu_to_r5SearchPatient);
            }
        });
        binding.weeklyplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DocMenuFragment.this)
                        .navigate(R.id.action_SecondFragment_to_weeklyProgramm);

            }
        });
        binding.viewrequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigate to r7DisplayAndManagementOfRequests & send the User Data
                NavHostFragment.findNavController(DocMenuFragment.this)
                        .navigate(R.id.action_DocMenu_to_r7DisplayAndManagementOfRequests,bundle);

            }
        });
        binding.addvisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigate to r8VisitAdditionAndActionLoggin & send the User Data
                NavHostFragment.findNavController(DocMenuFragment.this)
                        .navigate(R.id.action_DocMenu_to_r8VisitAdditionAndActionLoggin,bundle);

            }
        });
        binding.imageViewSignout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DocMenuFragment.this).navigate(R.id.action_DocMenu_to_selectRole);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}