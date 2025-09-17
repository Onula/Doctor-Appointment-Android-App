package com.example.r3;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.r3.Model.DoctorDBConnector;
import com.example.r3.Model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class R8AddFragment extends Fragment implements View.OnClickListener, DatePicker.OnDateChangedListener {
    private boolean untilBtnClicked=false;
    private boolean fromBtnClicked=false;
    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1;
    private String starTimeOfVisit = null;
    private String endTimeOfVisit = null;
    private EditText AMKAText,provisionIDText ;
    private DatePicker datePicker ;
    private Button addBtn, fromBtn, untilBtn;
    private View mainView;
    private DoctorDBConnector docDB;
    private String userID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_r8_add2, container, false);

        //CONNECT DATABASE
        docDB =  new DoctorDBConnector();

        //CREAT VISIT AMKA & ProvisionID
        AMKAText = mainView.findViewById(R.id.AMKAText);  AMKAText.setError(null);
        provisionIDText = mainView.findViewById(R.id.provisionIDText); provisionIDText.setError(null);

        //CREATE AND GET DATE OF VISIT
        datePicker = mainView.findViewById(R.id.datePicker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) datePicker.setOnDateChangedListener(this);

        //BUTTONS
        addBtn = mainView.findViewById(R.id.addButton); addBtn.setOnClickListener(this);
        fromBtn = mainView.findViewById(R.id.fromBtn); fromBtn.setOnClickListener(this);
        untilBtn = mainView.findViewById(R.id.untilBtn); untilBtn.setOnClickListener(this);

        return mainView;
    }
    @Override
    public void onClick(View view) {//WHEN CLICKED ADD BUTTON
        if (view == fromBtn) {
            openTimePickerDialog(fromBtn);
            if (!fromBtn.getText().equals("ΑΠΟ")) {
                fromBtn.setBackgroundColor(Color.GRAY);
                starTimeOfVisit = fromBtn.getText()+":00";
                fromBtnClicked = true;
            } else fromBtnClicked = false;
        } else if (view == untilBtn) {
            openTimePickerDialog(untilBtn);
            if (!untilBtn.getText().equals("MEXRI")) {
                untilBtn.setBackgroundColor(Color.GRAY);
                endTimeOfVisit = untilBtn.getText()+":00";
                untilBtnClicked = true;
            } else untilBtnClicked = false;
        } else if (view == addBtn) {
            if (AMKAText.length() != 11) { // ERROR
                AMKAText.setError("ΤΟ ΑΜΚΑ ΠΡΕΠΕΙ ΝΑ ΕΙΝΑΙ 11 ΨΗΦΙΑ");
                Toast.makeText(getContext(), "ΤΟ ΑΜΚΑ ΠΡΕΠΕΙ ΝΑ ΕΙΝΑΙ 11 ΨΗΦΙΑ", Toast.LENGTH_SHORT).show();
            }
            else if(docDB.getPatientWithAMKA(AMKAText.getText().toString()) == null){
                AMKAText.setError("ΔΕΝ ΥΠΑΡΧΕΙ ΑΣΘΕΝΗΣ ΜΕ ΑΥΤΟ ΤΟ ΑΜΚΑ");
                Toast.makeText(getContext(), "ΔΕΝ ΥΠΑΡΧΕΙ ΑΣΘΕΝΗΣ ΜΕ ΑΥΤΟ ΤΟ ΑΜΚΑ", Toast.LENGTH_SHORT).show();
            }
            else if (fromBtnClicked == true & untilBtnClicked == true) {
                //ADDIN VISIT
                String AMKA = AMKAText.getText().toString();
                String date = new SimpleDateFormat("yyyy-mm-dd").format(createDateFrom(datePicker));
                String provisionID = provisionIDText.getText().toString();

                boolean result = docDB.insertVisit(AMKA, provisionID, userID, date, starTimeOfVisit, endTimeOfVisit);
                if(!result){
                    Toast.makeText(getContext(), "ΔΕΝ ΠΡΟΣΤΕΘΗΚΕ ΠΡΟΕΚΥΨΕ ΣΦΑΛΜΑ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "ΕΠΙΤΥΧΗΣ ΠΡΟΣΘΗΚΗ ΕΠΙΣΚΕΨΗΣ", Toast.LENGTH_SHORT).show();
                    AMKAText.setText("");
                    provisionIDText.setText("");
                    fromBtn.setText("ΑΠΟ");
                    untilBtn.setText("MEXRI");
                }

            } else {
                if (!fromBtnClicked) {  // ERROR
                    Toast.makeText(getContext(), "ΔΙΑΛΕΞΕ ΑΡΧΙΚΗ ΩΡΑ", Toast.LENGTH_SHORT).show();
                    fromBtn.setBackgroundColor(Color.RED);
                } else if (!untilBtnClicked) {  // ERROR
                    Toast.makeText(getContext(), "ΔΙΑΛΕΞΕ ΤΕΛΙΚΗ ΩΡΑ", Toast.LENGTH_SHORT).show();
                    untilBtn.setBackgroundColor(Color.RED);
                }
            }
        }
    }
    public Date createDateFrom(DatePicker dp){//Convert the data of time and date picker to Date
        int day = dp.getDayOfMonth();
        int month = dp.getMonth() ;
        int year = dp.getYear();
        //EXAMPLE : date = Tue Apr 18 05:47:00 GMT+03:00 2023
        return new GregorianCalendar(year, month, day).getTime() ;
    }
    @Override
    public void onDateChanged(DatePicker datePicker, int year, int month, int date) {

    }
    public void openTimePickerDialog(Button btn) {
        boolean is24HView = true;
        Calendar c = Calendar.getInstance();
        // Time Set Listener.
        TimePickerDialog.OnTimeSetListener timeSetListener = (view, hourOfDay, minute) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm ");

            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);

            String formatted = sdf.format(c.getTime());
            btn.setText(formatted);

            lastSelectedHour = hourOfDay;
            lastSelectedMinute = minute;
        };
        // Create TimePickerDialog:
        TimePickerDialog timePickerDialog = new TimePickerDialog(mainView.getContext(),
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                timeSetListener, lastSelectedHour, lastSelectedMinute, is24HView);
        // Show
        timePickerDialog.show();
    }
    //Class that taking the Doctor UserID from the Sign=>DocMenuFragment=>R8VisitAdditionAndActionLoggin=> ViewPageAdapter
    public void setUserID(String id){
        userID = id;
    }
}