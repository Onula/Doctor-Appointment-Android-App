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

import com.example.r3.Model.DoctorDBConnector;
import com.example.r3.Model.Patient;
import com.example.r3.Model.User;
import com.example.r3.databinding.FragmentR3CreatePatientBinding;


public class R3CreatePatient extends Fragment {

    Patient patient;
    private FragmentR3CreatePatientBinding binding;
    static String userID = "O";
    User doctor = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentR3CreatePatientBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        //Create connection with DataBase
        DoctorDBConnector DBconnector = new DoctorDBConnector();
        //Taking the Doctor UserID from the bundle
        userID = getArguments().getString("UserID");

        Button button = binding.createButton;
        boolean result;
        binding.createButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                EditText Firstname = binding.nameText;
                EditText Lastname = binding.lastnameText;
                EditText Address = binding.addressText;
                EditText AMKA = binding.amkaText;

                String NameText = Firstname.getText().toString();
                String LastnameText = Lastname.getText().toString();
                String AddressText = Address.getText().toString();
                String AMKAText = AMKA.getText().toString();

                boolean result = DBconnector.insertPatient(userID,NameText, LastnameText, AddressText, AMKAText);

                if(DBconnector.getPatientWithAMKA(AMKAText) != null) message2();
                else if(result) message1();
                else message3();

                Firstname.setText("");
                Lastname.setText("");
                Address.setText("");
                AMKA.setText("");
            }
        });

        //με το πάτημα της εικόνας οδηγούμαστε στην αρχική οθόνη
        binding.homePic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });
        //με το πάτημα της εικόνας γίνεται αποσύνδεση από τον λογαριασμό
        binding.signoutPic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NavHostFragment.findNavController(R3CreatePatient.this)
                        .navigate(R.id.action_R3CreatePatient_to_selectRole);

            }

        });
        //με το πάτημα της εικόνας οδηγούμαστε στο μενού επιλογής
        binding.menuPic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NavHostFragment.findNavController(R3CreatePatient.this)
                        .navigate(R.id.action_R3CreatePatient_to_DocMenu);

            }

        });

    }


    //η μέθοδοι message 1, message2  εμφανίζουν με το πάτημα του κουμπιού μήνυμα ότι είναι επιτυχής
    // η καταχώριση αν καταχωρηθούν τα στοιχεία στην βάση, διαφορετικά το μήνυμα
    //ότι τα στοιχεία υπάρχουν ήδη στην βάση.


    public void message1 () {

        Toast myToast = Toast.makeText(getActivity(), "Επιτυχής καταχώριση", Toast.LENGTH_SHORT);

        myToast.show();

    }

    public void message2 () {

        Toast myToast = Toast.makeText(getActivity(), "Υπάρχει ήδη στη βάση ασθενής με αυτό το ΑΜΚΑ!!", Toast.LENGTH_SHORT);

        myToast.show();

    }
    public void message3 () {

        Toast myToast = Toast.makeText(getActivity(), "Κατι πήγε στραβά, προσπάθησε ξανά!!", Toast.LENGTH_SHORT);

        myToast.show();

    }


    @Override
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }

}
