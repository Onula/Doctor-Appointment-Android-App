package com.example.r3;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.r3.Model.AssociationDBConnector;
import com.example.r3.databinding.FragmentR2CreateProvisionBinding;


public class R2CreateProvision extends Fragment {

    private FragmentR2CreateProvisionBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentR2CreateProvisionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.Creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView code = binding.Code;
                TextView name = binding.Name;
                TextView description = binding.Description;
                TextView cost = binding.Cost;
                String codeText = code.getText().toString();
                String nameText = name.getText().toString();
                String descriptionText = description.getText().toString();
                String costText = cost.getText().toString();
                int error = 0;
                if (codeText.isEmpty() || nameText.isEmpty() || descriptionText.isEmpty() || costText.isEmpty()) {
                    Toast.makeText(getContext(), "Πρέπει να συμληρωθούν όλα τα πεδία", Toast.LENGTH_SHORT).show();
                    error = 1;
                    if (codeText.isEmpty()) {
                        code.setBackgroundColor(Color.RED);
                    }
                    if (nameText.isEmpty()) {
                        name.setBackgroundColor(Color.RED);
                    }
                    if (descriptionText.isEmpty()) {
                        description.setBackgroundColor(Color.RED);
                    }
                    if (costText.isEmpty()) {
                        cost.setBackgroundColor(Color.RED);
                    }
                }
                if (codeText.length() < 4 || codeText.length() > 4) {
                    Toast.makeText(getContext(), "Ο Κωδικός πρέπει να είναι 4 ψηφίων", Toast.LENGTH_LONG).show();
                    error = 1;
                    code.setBackgroundColor(Color.RED);
                }
                if (codeText.matches("\\d+") == false) {
                    Toast.makeText(getContext(), "Ο Κωδικός πρέπει να περιέχει μόνο αριθμούς", Toast.LENGTH_LONG).show();
                    error = 1;
                    code.setBackgroundColor(Color.RED);
                }
                if (costText.matches("\\d+") == false) {
                    Toast.makeText(getContext(), "Το κόστος ανά συνεδρία πρέπει να περιέχει μόνο αριθμούς", Toast.LENGTH_LONG).show();
                    error = 1;
                    cost.setBackgroundColor(Color.RED);
                }
                if (descriptionText.length() > 50) {
                    Toast.makeText(getContext(), "Η περιγραφή πρέπει να είναι μεγέθους μέχρι 50 χαρακτήρες", Toast.LENGTH_SHORT).show();
                    error = 1;
                    description.setBackgroundColor(Color.RED);
                }

                if (error == 0) {

                    AssociationDBConnector assDB = new AssociationDBConnector();

                    boolean answer = assDB.insertProvision(codeText, nameText, costText, descriptionText);
                    if (answer)
                        Toast.makeText(getContext(), "Επιτυχής καταχώρηση!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), "Ανεπιτυχής καταχώρηση ή υπάρχοντας Κωδικός", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.menuPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(R2CreateProvision.this)
                        .navigate(R.id.action_r2CreateProvision_to_PSFmemberMenu2);
            }
        });
        binding.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(R2CreateProvision.this)
                        .navigate(R.id.action_r2CreateProvision_to_selectRole);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}