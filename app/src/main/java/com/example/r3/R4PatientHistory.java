package com.example.r3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.r3.Model.AssociationDBConnector;
import com.example.r3.Model.DoctorDBConnector;
import com.example.r3.Model.HistoryData;
import com.example.r3.Model.Patient;
import com.example.r3.Model.PatientDBConnector;
import com.example.r3.databinding.FragmentR4PatientHistoryBinding;

import java.util.ArrayList;
import java.util.List;


public class R4PatientHistory extends Fragment {
    private FragmentR4PatientHistoryBinding binding;


    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentR4PatientHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        TextView userNameValueTextView;
        TextView userNameValue2TextView;
        TextView userAMKAValueTextView;
        TextView addressValueTextView;
        TextView emailValueTextView;
        ListView historyListView;

        List<HistoryData> historyDataList;

        DoctorDBConnector dbConnector = new DoctorDBConnector();

        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_history_r4);

        historyListView=binding.historyList;
        historyDataList = new ArrayList<>();

        userNameValueTextView = binding.userNameValue;
        userNameValue2TextView = binding.userNameValue2;
        userAMKAValueTextView = binding.userAmkaValue;
        addressValueTextView = binding.addressValue;
        emailValueTextView = binding.addressValue;

        String firstName = "John";
        String lastName = "Doe";
        String amka = "1234567890";
        String address = "123 Main Street";
        String email = "johndoe@example.com";
        Patient aPatient= new Patient(amka,firstName,lastName,address);

        userNameValueTextView.setText(firstName);
        userNameValue2TextView.setText(lastName);
        userAMKAValueTextView.setText(amka);
        addressValueTextView.setText(address);
        emailValueTextView.setText(email);





        historyDataList = dbConnector.getPatientHistory("1");
        List<String> historyItems = new ArrayList<>();
        for (HistoryData historyData : historyDataList) {
            historyItems.add(historyData.getProvision() + " - " + historyData.getDate());
        }

        ArrayAdapter<String> historyAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, historyItems);
        historyListView.setAdapter(historyAdapter);

        binding.homePic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R4PatientHistory.this)
                        .navigate(R.id.action_r4PatientHistory_to_DocMenu);
            }
        });

        binding.menuPic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R4PatientHistory.this)
                        .navigate(R.id.action_r4PatientHistory_to_DocMenu);

            }
        });

        binding.signoutPic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R4PatientHistory.this)
                        .navigate(R.id.action_r4PatientHistory_to_selectRole);

            }
        });


        }
    }

