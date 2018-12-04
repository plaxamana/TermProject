package com.philiplaxamana.comp304_termproject;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SmsFragment extends Fragment {


    public SmsFragment() {
        // Required empty public constructor
    }

    private EditText edit_sms_number;
    private Button btnSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sms, container, false);

        edit_sms_number = view.findViewById(R.id.edit_sms_number);
        btnSend = view.findViewById(R.id.btnSend);



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send SMS code
                String msg = "Hey, I'm using Count able+ to track my fitness progress. Download it now!";
//                Toast.makeText(getActivity(), "Sending message.. " + edit_sms_number.getText().toString(), Toast.LENGTH_SHORT).show();
                sendSMS(edit_sms_number.getText().toString(), msg);
            }
        });

        return view;
    }



    public void sendSMS(String phoneNo, String msg) {

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getActivity(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getActivity(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public void DisplayToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
