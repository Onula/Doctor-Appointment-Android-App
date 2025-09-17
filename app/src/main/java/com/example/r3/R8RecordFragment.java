package com.example.r3;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.r3.Model.DoctorDBConnector;
import com.example.r3.R;

import java.util.ArrayList;


public class R8RecordFragment extends Fragment {

    ListView listView;
    TextView textView;
    ArrayList<String> docActivities = new ArrayList<>() ;
    DoctorDBConnector docDB;
    String userID = null;

    ArrayAdapter<String> dataAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_r8_record2, container, false);
        listView =  view.findViewById(R.id.listView);
        textView = view.findViewById(R.id.textView);
        docDB = new DoctorDBConnector();
        docActivities.addAll(docDB.getDoctorActivity(userID));

        if(docActivities.isEmpty()){
            textView.setText("ΔΕΝ ΥΠΑΡΧΟΥΝ ΕΓΓΡΑΦΕΣ");
        }else{
            //SETTING ADAPTER
            dataAdapter = new ArrayAdapter<String>( getActivity().getApplicationContext(), android.R.layout.simple_list_item_1 , docActivities );
            //SETTING LIST
            listView.setAdapter(dataAdapter);
        }


        return view;
    }
    //Class that taking the Doctor UserID from the Sign=>DocMenuFragment=>R8VisitAdditionAndActionLoggin=> ViewPageAdapter
    public void setUserID(String id){
        userID = id;
    }
}