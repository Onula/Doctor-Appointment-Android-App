package com.example.r3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.r3.Model.DoctorDBConnector;
import com.example.r3.Model.Visit;
import com.example.r3.databinding.FragmentR6WeeklyProgrammBinding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class R6WeeklyProgramm extends Fragment {


    private FragmentR6WeeklyProgrammBinding binding;
    private String userID;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = FragmentR6WeeklyProgrammBinding.inflate(inflater, container, false);
        return binding.getRoot();

        /*Intent myIntent = new Intent(getActivity(), LoginActivity.class);
        myIntent.putExtra("key", value); //Optional parameters
        CurrentActivity.this.startActivity(myIntent);*/

    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        //Taking the Doctor UserID from the bundle
        userID = getArguments().getString("UserID");
        //Connection with DataBase
        DoctorDBConnector docDB = new DoctorDBConnector();
        ArrayList<Visit> visits;
        ArrayList<Button> ButtonList = new ArrayList();
        ArrayList<TextView> TextViewList = new ArrayList();
        super.onViewCreated(view, savedInstanceState);
        LinearLayout linearLayout = (LinearLayout) binding.lineartext;      //find the linear layout
        linearLayout.removeAllViews();                              //add this too
        for (int i = 1; i < 10; i++) {          //looping to create 5 textviews

            TextView textView = new TextView(getContext());              //dynamically create textview
            textView.setTextSize(18);//dynamically create textview
            textView.setLayoutParams(new LinearLayout.LayoutParams(             //select linearlayoutparam- set the width & height
                    ViewGroup.LayoutParams.MATCH_PARENT, 200));
            textView.setGravity(Gravity.CENTER_VERTICAL);                       //set the gravity too
            textView.setText(i + "11:00-12:00,Onomatepwnymo,Paroxh" + i);
            textView.setGravity(Gravity.CENTER);
            if (i == 0) textView.setPadding(20, 25, 0, 0);//adding text
            else textView.setPadding(0, 0, 0, 0);
            if (i == 9) textView.setPadding(0, 0, 0, 0);
            textView.setCompoundDrawables(null, null, null, null);
            linearLayout.addView(textView);
            TextViewList.add(textView);

            Button btnTag = new Button(getContext());
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(350, 80));
            btnTag.setText("Ακύρωση");
            btnTag.setPadding(0, 0, 0, 0);
            int red = Color.parseColor("#FF0000");
            btnTag.setBackgroundColor(red);
            btnTag.setId(i);
            linearLayout.addView(btnTag);
            ButtonList.add(btnTag);
            //btnTag.setOnClickListener(this);//inflating :)
        }
        for (int i = 0; i < ButtonList.size(); i++) {
            int finalI = i;
            ButtonList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextViewList.get(finalI).setText("Changed");
                    linearLayout.removeView(TextViewList.get(finalI));
                    linearLayout.removeView(ButtonList.get(finalI));
                }

            });

        binding.homePic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R6WeeklyProgramm.this)
                        .navigate(R.id.action_weeklyProgramm_to_SecondFragment);
            }
        });

        binding.menuPic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R6WeeklyProgramm.this)
                        .navigate(R.id.action_weeklyProgramm_to_SecondFragment);

            }
        });

        binding.signoutPic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(R6WeeklyProgramm.this)
                        .navigate(R.id.action_R6WeeklyProgramm_to_selectRole);

            }
        });
        }
        binding.buttonDeu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LocalDate buttonsDayDate=getMondayDate(LocalDate.now().getDayOfWeek().name());
                String buttonsDayDateString=buttonsDayDate.format(DateTimeFormatter.ISO_DATE);
                String[] DateTable =buttonsDayDateString.split("-");
                String StringfinalDate = DateTable[2] + "-"+DateTable[1] +"-"+ DateTable[0];
                String DatabaseDate=DateTable[0] + "-"+DateTable[1] +"-"+ DateTable[2];

                binding.daydate.setText(("Δευτέρα" + " " + StringfinalDate ));
            }
        });

        binding.buttonTri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LocalDate buttonsDayDate=getMondayDate(LocalDate.now().getDayOfWeek().name()).plusDays(1);
                String buttonsDayDateString=buttonsDayDate.format(DateTimeFormatter.ISO_DATE);
                String[] DateTable =buttonsDayDateString.split("-");
                String StringfinalDate = DateTable[2] + "-"+DateTable[1] +"-"+ DateTable[0];
                String DatabaseDate=DateTable[0] + "-"+DateTable[1] +"-"+ DateTable[2];

                binding.daydate.setText(("Τρίτη" + " " + StringfinalDate ));
            }
        });

        binding.buttonTet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LocalDate buttonsDayDate=getMondayDate(LocalDate.now().getDayOfWeek().name()).plusDays(2);
                String buttonsDayDateString=buttonsDayDate.format(DateTimeFormatter.ISO_DATE);
                String[] DateTable =buttonsDayDateString.split("-");
                String StringfinalDate = DateTable[2] + "-"+DateTable[1] +"-"+ DateTable[0];
                String DatabaseDate=DateTable[0] + "-"+DateTable[1] +"-"+ DateTable[2];

                binding.daydate.setText(("Τετάρτη" + " " + StringfinalDate ));
            }
        });

        binding.buttonPem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LocalDate buttonsDayDate=getMondayDate(LocalDate.now().getDayOfWeek().name()).plusDays(3);
                String buttonsDayDateString=buttonsDayDate.format(DateTimeFormatter.ISO_DATE);
                String[] DateTable =buttonsDayDateString.split("-");
                String StringfinalDate = DateTable[2] + "-"+DateTable[1] +"-"+ DateTable[0];
                String DatabaseDate=DateTable[0] + "-"+DateTable[1] +"-"+ DateTable[2];

                binding.daydate.setText(("Πέμπτη" + " " + StringfinalDate ));
            }
        });

        binding.buttonPar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LocalDate buttonsDayDate=getMondayDate(LocalDate.now().getDayOfWeek().name()).plusDays(4);
                String buttonsDayDateString=buttonsDayDate.format(DateTimeFormatter.ISO_DATE);
                String[] DateTable =buttonsDayDateString.split("-");
                String StringfinalDate = DateTable[2] + "-"+DateTable[1] +"-"+ DateTable[0];
                String DatabaseDate=DateTable[0] + "-"+DateTable[1] +"-"+ DateTable[2];

                binding.daydate.setText(("Παρασκευή" + " " + StringfinalDate ));
            }
        });
    }
    public LocalDate getMondayDate(String EngDay){
        if (EngDay == "FRIDAY") {
            return LocalDate.now().plusDays(-4);
        } else if (EngDay == "MONDAY") {
            return LocalDate.now();
        } else if (EngDay == "TUESDAY") {
            return LocalDate.now().plusDays(-1);
        } else if (EngDay == "WEDNESDAY") {
            return LocalDate.now().plusDays(-2);
        }
        else if (EngDay=="THURSDAY"){
            return LocalDate.now().plusDays(-3);
        }
        else if(EngDay=="SATURDAY"){
            return LocalDate.now().plusDays(2);
        }


        else return LocalDate.now().plusDays(1);

    }

    }
