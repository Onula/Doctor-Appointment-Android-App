package com.example.r3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.r3.Model.User;
import com.example.r3.Model.UserDBConnector;
import com.example.r3.databinding.FragmentSignInBinding;


public class SignIn extends Fragment {
    private User aUser;
    private FragmentSignInBinding binding;
    private UserDBConnector DBConnector = new UserDBConnector();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater, container, false);

        return binding.getRoot();
        // Inflate the layout for this fragment

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {



        Button signinbtn=binding.btnSignIn;
        EditText username=binding.editName;
        EditText password=binding.editPassword;


        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().length()==0 || password.getText().length()==0) {
                    Toast.makeText(getContext(),"Πρέπει και τα 2 πεδία να είναι συμπληρωμένα",Toast.LENGTH_SHORT).show();
                }
                else {
                    aUser=DBConnector.getUser(username.getText().toString(),password.getText().toString());

                    if(aUser==null){
                     Toast.makeText(getContext(),"Δεν υπάρχει χρήστης με τα εισαγμένα στοιχεία! Προσπαθήστε Ξανά",Toast.LENGTH_SHORT).show();
                     }else {
                        //Create bundle to send User data to fragments
                        Bundle bundle = new Bundle();
                        bundle.putString("UserID", aUser.getUserID());

                        if (aUser.getUserType().equals("1") ) {
                            //Navigate to PSFmemberMenu & send the PSF User data
                            NavHostFragment.findNavController(SignIn.this).navigate(R.id.action_selectRole_to_PSFmemberMenu2,bundle);

                        }else if(aUser.getUserType().equals("2") ) {
                            //Navigate to DocMenu & send the doctor User data
                            NavHostFragment.findNavController(SignIn.this).navigate(R.id.action_selectRole_to_DocMenu,bundle);
                        }
                        else if (aUser.getUserType().equals("3") ){
                            //Navigate to patientMenu & send the patient User data
                            NavHostFragment.findNavController(SignIn.this).navigate(R.id.action_selectRole_to_patientMenuFragment,bundle);
                        }
                    }

                    }


                }



        });

    }
}