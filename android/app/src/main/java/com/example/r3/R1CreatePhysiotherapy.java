package com.example.r3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.r3.Model.AssociationDBConnector;
import com.example.r3.databinding.FragmentR1CreatePhysiotherapyBinding;
import com.example.r3.databinding.FragmentR6WeeklyProgrammBinding;

public class R1CreatePhysiotherapy extends Fragment {

    private FragmentR1CreatePhysiotherapyBinding binding;
    private AssociationDBConnector assDB;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = FragmentR1CreatePhysiotherapyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        Button button = binding.button;

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText AFM = binding.AFM;
                EditText Name = binding.Name;
                EditText Adress = binding.Adress;

                String AFMText = AFM.getText().toString();
                String NameText = Name.getText().toString();
                String AdressText = Adress.getText().toString();

                assDB = new AssociationDBConnector();
                boolean result = assDB.insertPhysiotherapy(AFMText, NameText, AdressText);
                if(result) Toast.makeText(getContext(), "Επιτυχής Δημιουργία", Toast.LENGTH_LONG).show();
                else Toast.makeText(getContext(), "Ανεπιτυχής καταχώρηση, προσπάθησε ξανά", Toast.LENGTH_LONG).show();
            }

        } );
        binding.menuPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(R1CreatePhysiotherapy.this)
                        .navigate(R.id.action_r1CreatePhysiotherapy_to_PSFmemberMenu2);
            }
        });
        binding.signoutPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(R1CreatePhysiotherapy.this)
                        .navigate(R.id.action_R1CreatePhysiotherapy_to_selectRole);
            }
        });
    }
}